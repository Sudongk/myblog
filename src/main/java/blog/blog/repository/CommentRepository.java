package blog.blog.repository;

import blog.blog.model.Article;
import blog.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleOrderByIdDesc(Article article);
}
