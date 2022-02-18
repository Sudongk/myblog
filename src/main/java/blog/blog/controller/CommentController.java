package blog.blog.controller;

import blog.blog.dto.CommentDTO;
import blog.blog.model.Article;
import blog.blog.model.Comment;
import blog.blog.service.ArticleService;
import blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommentController {

    private CommentService commentService;
    private ArticleService articleService;

    @Autowired
    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @PutMapping("/comment/enroll/{id}")
    public String enroll(@RequestBody String content, @PathVariable Long id) {
        Article article = articleService.getArticleDetail(id);
        Comment comment = commentService.enrollComment(content, article);
        return comment.getContent();
    }

    @GetMapping("/comment/delete")
    public void deleteComment(@RequestParam Long id) {
        commentService.deleteComment(id);
    }

    @GetMapping("/comment/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Comment comment = commentService.getComment(id);
        return comment.getContent();
    }

    @PutMapping("/comment/update/{id}")
    public Optional<Comment> update(@PathVariable Long id, CommentDTO commentDTO) {
        Optional<Comment> updatedComment = commentService.updateComment(id, commentDTO);
        return updatedComment;
    }
}
