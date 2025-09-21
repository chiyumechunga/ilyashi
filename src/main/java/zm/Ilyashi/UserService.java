package zm.Ilyashi;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User RegisterUser(String username, String plainTextPassword){
        if (userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException();
        }
        User newUser = new User();
        newUser.setUsername(username);

        return newUser;
    }



}
