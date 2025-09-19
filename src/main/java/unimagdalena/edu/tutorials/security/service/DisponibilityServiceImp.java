package unimagdalena.edu.tutorials.security.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.DisponibilityDTO;
import unimagdalena.edu.tutorials.dto.DisponibilityMapper;
import unimagdalena.edu.tutorials.entity.Disponibility;
import unimagdalena.edu.tutorials.repositories.DisponibilityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisponibilityServiceImp implements DisponibilityService {

    private final DisponibilityRepository disponibilityRepository;
    private final DisponibilityMapper disponibilityMapper;
    private final UserService userService;

    public DisponibilityServiceImp(DisponibilityRepository disponibilityRepository, DisponibilityMapper disponibilityMapper, UserService userService) {
        this.disponibilityRepository = disponibilityRepository;
        this.disponibilityMapper = disponibilityMapper;
        this.userService = userService;
    }


    @Override
    public Optional<DisponibilityDTO> findDisponibilityById(Long id) {
        return disponibilityRepository.findById(id).map(disponibilityMapper::toDto);
    }

    @Override
    public List<DisponibilityDTO> findAllDisponibilities() {
        return disponibilityRepository.findAll()
                .stream()
                .map(disponibilityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DisponibilityDTO saveDisponibility(DisponibilityDTO disponibilityDTO) {
        Disponibility disponibility = disponibilityRepository.save(disponibilityMapper.toEntity(disponibilityDTO, userService));
        return disponibilityMapper.toDto(disponibility);
    }

    @Override
    public void deleteDisponibilityById(Long id) {
        disponibilityRepository.deleteById(id);
    }

    @Override
    public Optional<DisponibilityDTO> updateDisponibilityById(Long id, DisponibilityDTO disponibilityDTO) {
        return disponibilityRepository.findById(id).map(
                disponibilityInBD->{
                    disponibilityInBD.setDisponibilityDate(disponibilityDTO.disponibilityDate());
                    disponibilityInBD.setStartTime(disponibilityDTO.startTime());
                    disponibilityInBD.setEndTime(disponibilityDTO.endTime());

                    return disponibilityRepository.save(disponibilityInBD);
                }
        ).map(disponibilityMapper::toDto);
    }
}
