package pt.davidws.loginsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pt.davidws.loginsystem.domain.User;
import pt.davidws.loginsystem.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String name, String email, String password) {
        if (userRepository.findByEmailIgnoreCase(email).isPresent()) {
            throw new RuntimeException("Email já registado!");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password)); // MUITO IMPORTANTE: Criptografar a pass!

        userRepository.save(newUser);
    }
}