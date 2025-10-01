package unimagdalena.edu.tutorials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unimagdalena.edu.tutorials.dto.RoleDTO;
import unimagdalena.edu.tutorials.entity.ERole;
import unimagdalena.edu.tutorials.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
