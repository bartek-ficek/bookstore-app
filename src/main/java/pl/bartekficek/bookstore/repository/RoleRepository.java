package pl.bartekficek.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import pl.bartekficek.bookstore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
