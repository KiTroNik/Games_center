package pl.studia.ecommerence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studia.ecommerence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
