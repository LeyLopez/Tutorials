package unimagdalena.edu.tutorials.security.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.SubjectDTO;
import unimagdalena.edu.tutorials.dto.SubjectMapper;
import unimagdalena.edu.tutorials.entity.Subject;
import unimagdalena.edu.tutorials.repositories.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImp implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImp(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }


    @Override
    public Optional<SubjectDTO> findSubjectById(Long id) {
        return subjectRepository.findById(id).map(subjectMapper::toDTO);
    }

    @Override
    public List<SubjectDTO> findAllSubjects() {
        return subjectRepository.findAll().stream().map(subjectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.save(subjectMapper.toEntity(subjectDTO));
        return subjectMapper.toDTO(subject);
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Optional<SubjectDTO> updateSubjectById(Long id, SubjectDTO subjectDTO) {
        return subjectRepository.findById(id).map(
                subjectInBD->{
                    subjectInBD.setName(subjectDTO.name());
                    subjectInBD.setDescription(subjectDTO.description());

                    return subjectRepository.save(subjectInBD);
                }
        ).map(subjectMapper::toDTO);
    }
}
