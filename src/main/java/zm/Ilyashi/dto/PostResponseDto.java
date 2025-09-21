package zm.Ilyashi.dto;

import lombok.Getter;
import lombok.Setter;
import zm.Ilyashi.PostStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
private  Long id;
private String title;
private String content;
private PostStatus status;
private LocalDateTime createdAt;

private String authorName;

    public PostResponseDto(Long id, String title, String content, PostStatus status, LocalDateTime createdAt, String authorName) {
    }
}
