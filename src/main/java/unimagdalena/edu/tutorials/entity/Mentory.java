package unimagdalena.edu.tutorials.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mentories")
public class Mentory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoryId;

    @ManyToOne(targetEntity = User.class)
    private User tutor;

    @ManyToOne(targetEntity = Subject.class)
    private Subject subject;

    @Column(nullable = false)
    private LocalDate mentoryDate;

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Time endTime;

    @Enumerated(EnumType.STRING)
    private Modality modality;

    @Column(nullable = false)
    private Integer maxCapacity;

    private Integer availableCapacity;

    @Enumerated(EnumType.STRING)
    private MentoryStatus status;

    @JsonIgnore
    @OneToMany(targetEntity = Registration.class, mappedBy = "mentory", fetch = FetchType.LAZY)
    private Set<Registration> registrations;

}
