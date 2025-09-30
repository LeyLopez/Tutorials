package unimagdalena.edu.tutorials.security.service;

import unimagdalena.edu.tutorials.dto.SubjectDTO;
import unimagdalena.edu.tutorials.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectDTO> findSubjectById(Long id);
    List<SubjectDTO> findAllSubjects();
    SubjectDTO saveSubject(SubjectDTO subjectDTO);
    void deleteSubjectById(Long id);
    Optional<SubjectDTO> updateSubjectById(Long id, SubjectDTO subjectDTO);
    Subject findById(Long id);
}
