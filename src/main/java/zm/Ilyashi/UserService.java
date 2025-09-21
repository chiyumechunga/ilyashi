package zm.Ilyashi;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with a unique username and alias.
     * Encrypts the user's password before saving.
     */
    @Transactional
    public User registerUser(String username, String plainTextPassword, String alias) {
        // Business Rule 1: Username must be unique
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username '" + username + "' is already taken.");
        }

        // Business Rule 2: Alias must be unique
        if (userRepository.findByAlias(alias).isPresent()) {
            throw new IllegalStateException("Alias '" + alias + "' is already taken.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setAlias(alias);

        // Encrypt the password before storing it
        newUser.setPassword(passwordEncoder.encode(plainTextPassword));

        return userRepository.save(newUser);
    }

    /**
     * Finds a user by their ID.
     * This is required by the PostController.
     * @throws RuntimeException if user is not found.
     */
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
