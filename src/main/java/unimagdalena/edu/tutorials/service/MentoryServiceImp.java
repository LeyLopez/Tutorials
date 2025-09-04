package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.MentoryDTO;
import unimagdalena.edu.tutorials.dto.MentoryMapper;
import unimagdalena.edu.tutorials.entity.Mentory;
import unimagdalena.edu.tutorials.entity.MentoryStatus;
import unimagdalena.edu.tutorials.repositories.MentoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentoryServiceImp implements MentoryService {

    private final MentoryRepository mentoryRepository;
    private final MentoryMapper mentoryMapper;
    private final UserService userService;
    private final SubjectService subjectService;

    public MentoryServiceImp(MentoryRepository mentoryRepository, MentoryMapper mentoryMapper, UserService userService, SubjectService subjectService) {
        this.mentoryRepository = mentoryRepository;
        this.mentoryMapper = mentoryMapper;
        this.userService = userService;
        this.subjectService = subjectService;
    }


    @Override
    public List<MentoryDTO> findAllMentories() {
        return mentoryRepository.findAll()
                .stream()
                .map(mentoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MentoryDTO> findMentoryById(Long id) {
        return mentoryRepository.findById(id).map(mentoryMapper::toDto);
    }

    @Override
    public MentoryDTO saveMentory(MentoryDTO mentoryDTO) {
        Mentory mentory = mentoryRepository.save(mentoryMapper.toEntity(mentoryDTO, userService, subjectService));
        return mentoryMapper.toDto(mentory);
    }

    @Override
    public Optional<MentoryDTO> updateMentorById(Long id, MentoryDTO mentoryDTO) {
        return mentoryRepository.findById(id).map(
                mentoryInBD -> {
                    mentoryInBD.setMentoryDate(mentoryDTO.mentoryDate());
                    mentoryInBD.setStartTime(mentoryDTO.startTime());
                    mentoryInBD.setEndTime(mentoryDTO.endTime());
                    mentoryInBD.setMaxCapacity(mentoryDTO.maxCapacity());
                    mentoryInBD.setAvailableCapacity(mentoryDTO.availableCapacity());
                    mentoryInBD.setStatus(MentoryStatus.valueOf(mentoryDTO.status()));

                    return mentoryRepository.save(mentoryInBD);

                }
        ).map(mentoryMapper::toDto);
    }

    @Override
    public void deleteMentorById(Long id) {
        mentoryRepository.deleteById(id);
    }
}
