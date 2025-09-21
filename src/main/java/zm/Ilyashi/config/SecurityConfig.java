package zm.Ilyashi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF protection for our stateless REST API
                .csrf(csrf -> csrf.disable())

                // 2. Define the authorization rules for each endpoint
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to view posts
                        .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
                        // Allow anyone to register a new user
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )

                // 3. Enable HTTP Basic Authentication as our login method
                .httpBasic(withDefaults());

        return http.build();
    }
}

