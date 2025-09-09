package unimagdalena.edu.tutorials.service;

import unimagdalena.edu.tutorials.dto.MentoryDTO;
import unimagdalena.edu.tutorials.entity.Mentory;

import java.util.List;
import java.util.Optional;

public interface MentoryService {
    List<MentoryDTO> findAllMentories();
    Optional<MentoryDTO> findById(Long id);
    MentoryDTO saveMentory(MentoryDTO mentorDTO);
    Optional<MentoryDTO> updateMentorById(Long id, MentoryDTO mentorDTO);
    void deleteMentorById(Long id);
    Mentory findMentoryById(Long id);
}
