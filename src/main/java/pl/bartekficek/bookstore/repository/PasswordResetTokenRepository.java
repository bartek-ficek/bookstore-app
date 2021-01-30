package pl.bartekficek.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekficek.bookstore.domain.security.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}
