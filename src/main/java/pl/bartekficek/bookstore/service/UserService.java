package pl.bartekficek.bookstore.service;

import pl.bartekficek.bookstore.domain.User;
import pl.bartekficek.bookstore.domain.security.PasswordResetToken;
import pl.bartekficek.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail(String email);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
