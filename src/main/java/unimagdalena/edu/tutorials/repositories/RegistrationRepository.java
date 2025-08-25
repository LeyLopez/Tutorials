package unimagdalena.edu.tutorials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unimagdalena.edu.tutorials.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
