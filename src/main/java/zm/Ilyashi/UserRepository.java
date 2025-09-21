package zm.Ilyashi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their unique username.
     * @param username The username to search for.
     * @return An Optional containing the User if found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their unique alias.
     * @param alias The alias to search for.
     * @return An Optional containing the User if found.
     */
    Optional<User> findByAlias(String alias);
}
