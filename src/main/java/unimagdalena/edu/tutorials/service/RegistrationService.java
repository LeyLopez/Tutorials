package unimagdalena.edu.tutorials.service;

import unimagdalena.edu.tutorials.dto.RegistrationDTO;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    Optional<RegistrationDTO> findRegistrationById(Long id);
    RegistrationDTO saveRegistration(RegistrationDTO registrationDTO);
    Optional<RegistrationDTO> updateRegistrationById(Long id, RegistrationDTO registrationDTO);
    void deleteRegistrationById(Long id);
    List<RegistrationDTO> findAllRegistrations();
}
