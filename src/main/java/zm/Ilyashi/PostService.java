package zm.Ilyashi;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zm.Ilyashi.dto.PostResponseDto;
import zm.Ilyashi.dto.PostUpdateRequestDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    // Define a security policy for HTML sanitization.
    // This policy allows basic formatting (bold, italic) and block elements (p, blockquote).
    private static final PolicyFactory HTML_SANITIZER_POLICY = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(Post post, User author) {
        // Sanitize user input to prevent XSS before saving.
        String sanitizedTitle = HTML_SANITIZER_POLICY.sanitize(post.getTitle());
        String sanitizedContent = HTML_SANITIZER_POLICY.sanitize(post.getContent());

        post.setTitle(sanitizedTitle);
        post.setContent(sanitizedContent);
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public List<PostResponseDto> findAllPublishedPosts() {
        List<Post> publishedPosts = postRepository.findByStatus(PostStatus.PUBLISHED);
        return publishedPosts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Post updatePost(Long postId, PostUpdateRequestDto updateDto, Long authorId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        if (!Objects.equals(post.getAuthor().getId(), authorId)) {
            throw new IllegalStateException("User is not authorized to update this post.");
        }

        // Sanitize user input to prevent XSS before saving.
        String sanitizedTitle = HTML_SANITIZER_POLICY.sanitize(updateDto.getTitle());
        String sanitizedContent = HTML_SANITIZER_POLICY.sanitize(updateDto.getContent());

        post.setTitle(sanitizedTitle);
        post.setContent(sanitizedContent);
        post.setStatus(updateDto.getStatus());
        post.setAnonymous(updateDto.isAnonymous());
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, Long authorId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        if (!Objects.equals(post.getAuthor().getId(), authorId)) {
            throw new IllegalStateException("User is not authorized to delete this post.");
        }
        postRepository.delete(post);
    }

    private PostResponseDto convertToDto(Post post) {
        String authorName = post.isAnonymous() ? "Anonymous" : post.getAuthor().getAlias();

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getStatus(),
                post.getCreatedAt(),
                authorName
        );
    }
}

