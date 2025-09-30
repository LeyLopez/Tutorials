package unimagdalena.edu.tutorials.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unimagdalena.edu.tutorials.dto.SubjectDTO;
import unimagdalena.edu.tutorials.exception.NotFoundException;
import unimagdalena.edu.tutorials.security.service.SubjectService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subject")
public class SubjectAPI {

    private final SubjectService subjectService;


    public SubjectAPI(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    private ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.findAllSubjects());
    }

    @GetMapping("/{id}")
    private ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
        return subjectService.findSubjectById(id)
                .map(subject-> ResponseEntity.ok().body(subject))
                .orElseThrow(()-> new NotFoundException("The subject with id " + id + " does not exist."));
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createdSubject(@RequestBody SubjectDTO subjectDTO) {
        return createSubject(subjectDTO);
    }

    private ResponseEntity<SubjectDTO> createSubject(SubjectDTO subjectDTO) {

        SubjectDTO newSubject = subjectService.saveSubject(subjectDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(newSubject.subjectId()).toUri();

        return ResponseEntity.created(location).body(newSubject);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectDTO subjectDTO) {
        Optional<SubjectDTO> subjectToUpdate = subjectService.updateSubjectById(id, subjectDTO);
        return subjectToUpdate
                .map(subject->ResponseEntity.ok().body(subject))
                .orElseGet(()->{return createSubject(subjectDTO);});
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<SubjectDTO> deleteSubject(@PathVariable Long id) {
        return subjectService.findSubjectById(id).map(
                subject->{
                    subjectService.deleteSubjectById(id);
                    return ResponseEntity.ok().body(subject);
                }
        ).orElseThrow(()->new NotFoundException("The subject with id " + id + " does not exist."));
    }


}
