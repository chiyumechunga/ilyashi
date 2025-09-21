package zm.Ilyashi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zm.Ilyashi.dto.PostResponseDto;
import zm.Ilyashi.dto.PostUpdateRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPublishedPosts() {
        List<PostResponseDto> posts = postService.findAllPublishedPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        User author = userService.findUserById(1L);
        Post createdPost = postService.createPost(post, author);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto updateDto) {
        Long currentUserId = 1L;
        Post updatedPost = postService.updatePost(id, updateDto, currentUserId);
        return ResponseEntity.ok(updatedPost);
    }

    /**
     * Endpoint to delete an existing post.
     * @param id The ID of the post to delete, captured from the URL.
     * @return A response entity with no content to indicate success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        // --- IMPORTANT ---
        // We simulate getting the authenticated user ID.
        Long currentUserId = 1L;

        postService.deletePost(id, currentUserId);

        // A 204 No Content status is a standard successful response for a DELETE action.
        return ResponseEntity.noContent().build();
    }
}

