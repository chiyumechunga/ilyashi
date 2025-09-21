package zm.Ilyashi.dto;
import lombok.Getter;
import lombok.Setter;
import zm.Ilyashi.PostStatus;

@Getter
@Setter
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private PostStatus status;
    private boolean isAnonymous;
}
