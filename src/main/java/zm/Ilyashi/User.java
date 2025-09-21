package zm.Ilyashi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // For login

    @Column(nullable = false)
    private String password; // Stored encoded

    @Column(unique = true, nullable = false)
    private String alias; // Public-facing "pen name"
}
