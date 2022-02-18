package blog.blog.dto;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class ArticleDTO {
    private final String title;
    private final String name;
    private final String content;
}
