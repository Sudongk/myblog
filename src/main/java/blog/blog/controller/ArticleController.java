package blog.blog.controller;

import blog.blog.dto.ArticleDTO;
import blog.blog.model.Comment;
import blog.blog.service.ArticleService;
import blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleController {

    private ArticleService articleService;
    private CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/article/create")
    public String createPage() {
        return "createPage";
    }

    @PostMapping("/article/create")
    public String create(ArticleDTO articleDTO) {
        articleService.registerArticle(articleDTO);
        return "redirect:/article/articles";
    }

    @GetMapping("/article/articles")
    public String articles(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "articlePage";
    }

    @GetMapping("/article/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        model.addAttribute("articleDetail", articleService.getArticleDetail(id));
        List<Comment> comments = commentService.getComments(id);
        model.addAttribute("comments", comments);
        return "articleDetail";
    }

    @GetMapping("/article/articles/delete")
    public void deleteArticle(@RequestParam Long id) {
        articleService.deleteArticle(id);
    }

    @GetMapping("/article/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("articleDetail", articleService.getArticleDetail(id));
        return "updateForm";
    }

    @PostMapping("/article/update/{id}")
    public String update(ArticleDTO articleDTO, @PathVariable Long id) {
        articleService.updateArticle(articleDTO, id);
        return "redirect:/article/articles/"+ id;
    }
}
