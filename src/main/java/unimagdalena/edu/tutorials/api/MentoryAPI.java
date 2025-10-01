package unimagdalena.edu.tutorials.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unimagdalena.edu.tutorials.dto.MentoryDTO;
import unimagdalena.edu.tutorials.exception.NotFoundException;
import unimagdalena.edu.tutorials.security.service.MentoryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mentory")
@CrossOrigin(origins = "*")
public class MentoryAPI {

    private final MentoryService mentoryService;

    public MentoryAPI(MentoryService mentoryService) {
        this.mentoryService = mentoryService;
    }

    @GetMapping
    private ResponseEntity<List<MentoryDTO>> getAllMentory(){
        return ResponseEntity.ok(mentoryService.findAllMentories());
    }

    @GetMapping("/{id}")
    private ResponseEntity<MentoryDTO> getMentoryById(@PathVariable("id") Long id){
        return mentoryService.findMentoryById(id)
                .map(mentory->ResponseEntity.ok().body(mentory))
                .orElseThrow(()->new NotFoundException("The mentory with id " + id + " does not exist."));
    }

    @PostMapping
    private ResponseEntity<MentoryDTO> createdMentory(@RequestBody MentoryDTO mentoryDTO){
        return createMentory(mentoryDTO);
    }

    private ResponseEntity<MentoryDTO> createMentory(MentoryDTO mentoryDTO) {
        MentoryDTO newMentory = mentoryService.saveMentory(mentoryDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMentory.mentoryId())
                .toUri();

        return ResponseEntity.created(location).body(newMentory);
    }

    @PutMapping("/{id}")
    private ResponseEntity<MentoryDTO> updateMentory(@PathVariable("id") Long id, @RequestBody MentoryDTO mentoryDTO){
        Optional<MentoryDTO> mentoryToUpdate = mentoryService.updateMentorById(id, mentoryDTO);
        return mentoryService.findMentoryById(id)
                .map(mentory-> ResponseEntity.ok().body(mentory))
                .orElseGet(()->{return createMentory(mentoryDTO);});
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<MentoryDTO> deleteMentory(@PathVariable("id") Long id){
        return mentoryService.findMentoryById(id).map(
                mentory->ResponseEntity.ok().body(mentory)
        ).orElseThrow(()->new NotFoundException("The mentory with id " + id + " does not exist."));
    }

}
