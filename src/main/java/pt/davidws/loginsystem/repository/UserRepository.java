package pt.davidws.loginsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.davidws.loginsystem.domain.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Atualizado para ignorar maiúsculas/minúsculas
    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByPasswordResetToken(String token);
}