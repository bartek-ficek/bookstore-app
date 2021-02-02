package pl.bartekficek.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementation of CommandLineRunner interface and run() method added here to create default user.
 * Very convenient to testing already ready functionalities.
 */
@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
