package unimagdalena.edu.tutorials.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unimagdalena.edu.tutorials.dto.DisponibilityDTO;
import unimagdalena.edu.tutorials.exception.NotFoundException;
import unimagdalena.edu.tutorials.security.service.DisponibilityService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disponibility")
@CrossOrigin(origins = "*")
public class DisponibilityAPI {

    private final DisponibilityService disponibilityService;


    public DisponibilityAPI(DisponibilityService disponibilityService) {
        this.disponibilityService = disponibilityService;
    }

    @GetMapping
    public ResponseEntity<List<DisponibilityDTO>> getAllDisponibilities() {
        return ResponseEntity.ok(disponibilityService.findAllDisponibilities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilityDTO> getDisponibilityById(@PathVariable Long id) {
        return disponibilityService.findDisponibilityById(id)
                .map(disponibility->ResponseEntity.ok().body(disponibility))
                .orElseThrow(()->new NotFoundException("The disponibility with id " + id + " does not exist."));
    }

    @PostMapping
    public ResponseEntity<DisponibilityDTO> createdDisponibility(@RequestBody DisponibilityDTO disponibilityDTO) {
        return createDisponibility(disponibilityDTO);
    }

    private ResponseEntity<DisponibilityDTO> createDisponibility(DisponibilityDTO disponibilityDTO) {
        DisponibilityDTO newDisponibility = disponibilityService.saveDisponibility(disponibilityDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDisponibility.disponibilityId()).toUri();

        return ResponseEntity.created(location).body(newDisponibility);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DisponibilityDTO> updateDisponibility(@PathVariable Long id, @RequestBody DisponibilityDTO disponibilityDTO) {
        Optional<DisponibilityDTO> disponibilityToUpdate = disponibilityService.updateDisponibilityById(id, disponibilityDTO);
        return disponibilityToUpdate
                .map(dispnibility->ResponseEntity.ok().body(dispnibility))
                .orElseGet(()->{return createDisponibility(disponibilityDTO);});
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisponibilityDTO> deleteDisponibilityById(@PathVariable Long id) {
        return disponibilityService.findDisponibilityById(id).map(disponibilityDTO -> {
            disponibilityService.deleteDisponibilityById(id);

            return ResponseEntity.ok().body(disponibilityDTO);
        }).orElseThrow(()-> new NotFoundException("The disponibily with the id " + id + " does not exist."));
    }
}
