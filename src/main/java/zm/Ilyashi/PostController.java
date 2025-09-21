package zm.Ilyashi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import zm.Ilyashi.dto.PostResponseDto;
import zm.Ilyashi.dto.PostUpdateRequestDto;

import java.util.List;

/**
 * The final, secure controller for handling all Post-related API requests.
 * It is fully integrated with Spring Security to manage authentication and authorization.
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * PUBLIC Endpoint to fetch all published posts.
     * This is accessible to everyone, including non-logged-in users.
     */
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPublishedPosts() {
        List<PostResponseDto> posts = postService.findAllPublishedPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * SECURE Endpoint to create a new post.
     * It uses @AuthenticationPrincipal to get the logged-in user's details from the security context.
     * It now returns a safe DTO instead of the raw entity.
     */
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody Post post, @AuthenticationPrincipal UserDetails userDetails) {
        // Find the full author User entity from the username provided by Spring Security.
        User author = userService.findByUsername(userDetails.getUsername());

        Post createdPost = postService.createPost(post, author);

        // Convert the created entity to a safe DTO for the response.
        PostResponseDto responseDto = postService.convertToDto(createdPost);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * SECURE Endpoint to update an existing post.
     * The identity of the user making the request is taken from the security context.
     * It now returns a safe DTO instead of the raw entity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id,
                                                      @RequestBody PostUpdateRequestDto updateDto,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        // Find the current user making the request.
        User currentUser = userService.findByUsername(userDetails.getUsername());

        // Pass the user's ID to the service for the authorization check.
        Post updatedPost = postService.updatePost(id, updateDto, currentUser.getId());

        // Convert the updated entity to a safe DTO for the response.
        PostResponseDto responseDto = postService.convertToDto(updatedPost);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * SECURE Endpoint to delete a post.
     * The identity of the user making the request is taken from the security context.
     * Authorization is handled in the service layer.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        // Find the current user making the request.
        User currentUser = userService.findByUsername(userDetails.getUsername());

        // Pass the user's ID to the service for the authorization check.
        postService.deletePost(id, currentUser.getId());

        // Return a 204 No Content response, which is the standard for successful delete operations.
        return ResponseEntity.noContent().build();
    }
}

