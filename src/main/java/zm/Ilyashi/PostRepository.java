package zm.Ilyashi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
    /**
     * Finds all Post entities that have a specific status.
     * Spring Data JPA will automatically implement this method for us
     * based on the method name. It generates the SQL:
     * "SELECT * FROM posts WHERE status = ?"
     *
     * @param status The PostStatus enum to search for (e.g., PUBLISHED).
     * @return A list of posts matching the status.
     */
    List<Post> findByStatus(PostStatus status); // ✅ This is the required method
}
