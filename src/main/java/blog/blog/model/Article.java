package blog.blog.model;

import blog.blog.dto.ArticleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false)
    private String content;

//    @OneToMany(mappedBy = "article")
//    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Article(String title, String name, String content) {
        this.title = title;
        this.name = name;
        this.content = content;
    }

    public void update(ArticleDTO articleDTO) {
        this.title = articleDTO.getTitle();
        this.name = articleDTO.getName();
        this.content = articleDTO.getContent();
    }
}
