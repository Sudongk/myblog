package blog.blog.service;

import blog.blog.dto.ArticleDTO;
import blog.blog.dto.CommentDTO;
import blog.blog.model.Article;
import blog.blog.model.Comment;
import blog.blog.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;

    @Test
    @Commit
    public void 게시글_등록() {
        ArticleDTO articleDTO = new ArticleDTO("spring", "김수동", "backend");
        articleService.registerArticle(articleDTO);
    }

    @Test
    public void 전체게시글_조회() {
        List<Article> articles = articleService.getArticles();
        System.out.println(articles);

    }

    @Test
    @Commit
    public void 댓글_수정() {
        CommentDTO commentDTO = new CommentDTO("aaaaa");
        Optional<Comment> comment = commentService.updateComment(2L, commentDTO);
        assertThat(comment.get().getContent()).isEqualTo(commentDTO.getContent());
    }

}