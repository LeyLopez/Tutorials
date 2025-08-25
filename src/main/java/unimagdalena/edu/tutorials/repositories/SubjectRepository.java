package unimagdalena.edu.tutorials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unimagdalena.edu.tutorials.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
