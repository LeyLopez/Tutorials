package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.RegistrationDTO;
import unimagdalena.edu.tutorials.dto.RegistrationMapper;
import unimagdalena.edu.tutorials.entity.Mentory;
import unimagdalena.edu.tutorials.entity.Registration;
import unimagdalena.edu.tutorials.entity.RegistrationStatus;
import unimagdalena.edu.tutorials.repositories.RegistrationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImp implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final UserService userService;
    private final MentoryService mentoryService;

    public RegistrationServiceImp(RegistrationRepository registrationRepository, RegistrationMapper registrationMapper, UserService userService, MentoryService mentoryService) {
        this.registrationRepository = registrationRepository;
        this.registrationMapper = registrationMapper;
        this.userService = userService;
        this.mentoryService = mentoryService;
    }


    @Override
    public Optional<RegistrationDTO> findRegistrationById(Long id) {
        return registrationRepository.findById(id).map(registrationMapper::toDto);
    }

    @Override
    public RegistrationDTO saveRegistration(RegistrationDTO registrationDTO) {
        Registration newRegistration = registrationRepository.save(registrationMapper.toEntity(registrationDTO, userService, mentoryService));
        return registrationMapper.toDto(newRegistration);
    }

    @Override
    public Optional<RegistrationDTO> updateRegistrationById(Long id, RegistrationDTO registrationDTO) {
        return registrationRepository.findById(id).map(
                registrationInBD->{
                    registrationInBD.setRegistrationDate(registrationDTO.registrationDate());
                    registrationInBD.setStatus(RegistrationStatus.valueOf(registrationDTO.status()));

                    return registrationRepository.save(registrationInBD);
                }
        ).map(registrationMapper::toDto);
    }

    @Override
    public void deleteRegistrationById(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public List<RegistrationDTO> findAllRegistrations() {
        return registrationRepository.findAll().stream().map(registrationMapper::toDto).collect(Collectors.toList());
    }
}
