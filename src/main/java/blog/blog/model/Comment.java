package blog.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // FK 주인
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private Article article;

    public Comment(String content, Article article) {
        this.content = content;
        this.article = article;
    }
}
