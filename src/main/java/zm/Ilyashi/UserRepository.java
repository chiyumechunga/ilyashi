package zm.Ilyashi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
// This method will find a user by their username
    Optional<User> findByUsername(String username);
}
