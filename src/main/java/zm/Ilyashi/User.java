package zm.Ilyashi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users") // Maps this class to the 'users' table in your database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Usernames should be unique and not empty
    private String username;

    @Column(nullable = false) // Passwords should not be empty
    private String password;

    @Enumerated(EnumType.STRING) // Store the role as text (e.g., "ROLE_USER")
    @Column(nullable = false)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}