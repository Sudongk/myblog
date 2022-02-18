package blog.blog.service;

import blog.blog.dto.ArticleDTO;
import blog.blog.model.Article;
import blog.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void registerArticle(ArticleDTO articleDTO) {
        Article article = Article.builder()
                .title(articleDTO.getTitle())
                .name(articleDTO.getName())
                .content(articleDTO.getContent())
                .build();

        articleRepository.save(article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Article getArticleDetail(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElseThrow(IllegalArgumentException::new);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public void updateArticle(ArticleDTO articleDTO, Long id) {
        Optional<Article> article = articleRepository.findById(id);
        article.ifPresentOrElse(
                    at -> at.update(articleDTO),
                    () -> {throw new IllegalStateException("존재하지 않는 게시글");}
                );
    }
}
