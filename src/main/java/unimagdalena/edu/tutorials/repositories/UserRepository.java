package unimagdalena.edu.tutorials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unimagdalena.edu.tutorials.dto.UserDTO;
import unimagdalena.edu.tutorials.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
