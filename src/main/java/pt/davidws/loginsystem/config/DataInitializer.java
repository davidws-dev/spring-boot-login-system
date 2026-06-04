package pt.davidws.loginsystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pt.davidws.loginsystem.domain.User;
import pt.davidws.loginsystem.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Se a base de dados estiver vazia, cria o utilizador de teste
        if (userRepository.count() == 0) {
            User testUser = new User();
            testUser.setEmail("dev@portugal.pt");

            // Repara na password encriptada com BCrypt!
            testUser.setPassword(passwordEncoder.encode("admin123"));

            userRepository.save(testUser);
            System.out.println(">>> Utilizador de teste criado com sucesso: dev@portugal.pt / admin123");
        }
    }
}