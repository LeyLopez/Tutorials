package unimagdalena.edu.tutorials.api;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unimagdalena.edu.tutorials.dto.RegistrationDTO;
import unimagdalena.edu.tutorials.dto.RegistrationMapper;
import unimagdalena.edu.tutorials.exception.NotFoundException;
import unimagdalena.edu.tutorials.service.RegistrationService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration")
public class RegistrationAPI {
    private final RegistrationService registrationService;


    public RegistrationAPI(RegistrationService registrationService, RegistrationMapper registrationMapper) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> getAllRegistrations(){
        return ResponseEntity.ok(registrationService.findAllRegistrations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDTO> getRegistrationById(@PathVariable Long id) {
        return registrationService.findRegistrationById(id)
                .map(registration->ResponseEntity.ok().body(registration))
                .orElseThrow(()->new NotFoundException("The registration with the id: " + id + " was not found"));
    }

    @PostMapping
    public ResponseEntity<RegistrationDTO> createdRegistration(@RequestBody RegistrationDTO registrationDTO) {
        return createRegistration(registrationDTO);
    }

    private ResponseEntity<RegistrationDTO> createRegistration(RegistrationDTO registrationDTO) {
        RegistrationDTO newRegistration = registrationService.saveRegistration(registrationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRegistration.registrationId()).toUri();
        return ResponseEntity.created(location).body(newRegistration);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDTO> updateRegistration(@PathVariable Long id, @RequestBody RegistrationDTO registrationDTO) {
        Optional<RegistrationDTO> registrationToUpdate = registrationService.updateRegistrationById(id, registrationDTO);
        return registrationToUpdate
                .map(registration-> ResponseEntity.ok().body(registration))
                .orElseGet(()->{return createRegistration(registrationDTO);});
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistrationDTO> deleteRegistration(@PathVariable Long id) {
        return registrationService.findRegistrationById(id)
                .map(registration->{
                    registrationService.deleteRegistrationById(id);
                    return ResponseEntity.ok().body(registration);
                }).orElseThrow(()->new NotFoundException("The registration with the id: " + id + " was not found"));
    }
}
