package unimagdalena.edu.tutorials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unimagdalena.edu.tutorials.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
