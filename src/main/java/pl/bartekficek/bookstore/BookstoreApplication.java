package pl.bartekficek.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.bartekficek.bookstore.domain.User;
import pl.bartekficek.bookstore.domain.security.Role;
import pl.bartekficek.bookstore.domain.security.UserRole;
import pl.bartekficek.bookstore.service.UserService;
import pl.bartekficek.bookstore.utility.SecurityUtility;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of CommandLineRunner interface and run() method added here to create default user.
 * Very convenient to testing already ready functionalities.
 */
@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setUsername("JK");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("JKowalski@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1,role1));

        userService.createUser(user1, userRoles);
    }
}
