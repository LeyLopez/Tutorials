package unimagdalena.edu.tutorials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unimagdalena.edu.tutorials.entity.Disponibility;

@Repository
public interface DisponibilityRepository extends JpaRepository<Disponibility, Long> {
}
