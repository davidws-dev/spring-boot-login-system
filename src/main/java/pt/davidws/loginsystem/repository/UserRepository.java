package pt.davidws.loginsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.davidws.loginsystem.domain.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // O Spring cria o SQL "SELECT * FROM users WHERE email = ?" automaticamente!
    Optional<User> findByEmail(String email);

    Optional<User> findByPasswordResetToken(String token);
}