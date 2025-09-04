package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.RegistrationDTO;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImp implements RegistrationService {



    @Override
    public Optional<RegistrationDTO> findRegistrationById(Long id) {
        return Optional.empty();
    }

    @Override
    public RegistrationDTO saveRegistration(RegistrationDTO registrationDTO) {
        return null;
    }

    @Override
    public Optional<RegistrationDTO> updateRegistrationById(Long id, RegistrationDTO registrationDTO) {
        return Optional.empty();
    }

    @Override
    public void deleteRegistrationById(Long id) {

    }

    @Override
    public List<RegistrationDTO> findAllRegistrations() {
        return List.of();
    }
}
