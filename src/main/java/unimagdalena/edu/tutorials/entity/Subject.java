package unimagdalena.edu.tutorials.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "subjects")
    private Set<User> tutors;

    @JsonIgnore
    @OneToMany(targetEntity = Mentory.class, mappedBy = "subject", fetch = FetchType.LAZY)
    private Set<Mentory> mentories;

}
