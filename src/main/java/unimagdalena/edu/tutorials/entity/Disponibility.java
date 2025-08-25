package unimagdalena.edu.tutorials.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disponibilities")
public class Disponibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disponibilityId;

    @Column(nullable = false)
    private LocalDate disponibilityDate;

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Time endTime;

    @Enumerated(EnumType.STRING)
    private Modality modality;

    @ManyToOne(targetEntity = User.class)
    private User tutor;
}
