package pl.bartekficek.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartekficek.bookstore.domain.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
