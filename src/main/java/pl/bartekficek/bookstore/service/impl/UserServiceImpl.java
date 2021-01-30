package pl.bartekficek.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekficek.bookstore.domain.User;
import pl.bartekficek.bookstore.domain.security.PasswordResetToken;
import pl.bartekficek.bookstore.repository.PasswordResetTokenRepository;
import pl.bartekficek.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }
}
