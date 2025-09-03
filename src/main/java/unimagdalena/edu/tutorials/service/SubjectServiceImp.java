package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.SubjectDTO;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImp implements SubjectService {
    @Override
    public Optional<SubjectDTO> findSubjectById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<SubjectDTO> findAllSubjects() {
        return List.of();
    }

    @Override
    public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
        return null;
    }

    @Override
    public void deleteSubjectById(Long id) {

    }

    @Override
    public Optional<SubjectDTO> updateSubjectById(Long id, SubjectDTO subjectDTO) {
        return Optional.empty();
    }
}
