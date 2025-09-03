package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.MentoryDTO;

import java.util.List;
import java.util.Optional;

@Service
public class MentoryServiceImp implements MentoryService {
    @Override
    public List<MentoryDTO> findAllMentories() {
        return List.of();
    }

    @Override
    public Optional<MentoryDTO> findMentoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public MentoryDTO saveMentory(MentoryDTO mentorDTO) {
        return null;
    }

    @Override
    public Optional<MentoryDTO> updateMentorById(Long id, MentoryDTO mentorDTO) {
        return Optional.empty();
    }

    @Override
    public void deleteMentorById(Long id) {

    }
}
