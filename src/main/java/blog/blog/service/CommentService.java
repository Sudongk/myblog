package blog.blog.service;

import blog.blog.dto.CommentDTO;
import blog.blog.model.Article;
import blog.blog.model.Comment;
import blog.blog.repository.ArticleRepository;
import blog.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Comment enrollComment(String content, Article article) {
        Comment comment = new Comment(content, article);
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        List<Comment> comments = commentRepository.findAllByArticleOrderByIdDesc(article.get());

        return comments;
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Optional<Comment> updateComment(Long id, CommentDTO commentDTO) {
        Optional<Comment> comment = commentRepository.findById(id);
        comment.ifPresentOrElse(
                c -> c.update(commentDTO),
                () -> {throw new IllegalStateException("존재하지 않는 댓글");}
        );
        return comment;
    }
}
