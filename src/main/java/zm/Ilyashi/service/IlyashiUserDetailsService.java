package zm.Ilyashi.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zm.Ilyashi.UserRepository;

import java.util.Collections;

public class IlyashiUserDetailsService {
    private final UserRepository userRepository;

    public IlyashiUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Find our custom User entity from the database.
        zm.Ilyashi.User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // 2. Convert our User entity into a UserDetails object that Spring Security understands.
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.emptyList() // We are not using roles/authorities yet
        );
    }


}
