package unimagdalena.edu.tutorials.dto;

import java.sql.Time;
import java.time.LocalDate;

public record DisponibilityDTO(
        Long disponibilityId,
        LocalDate disponibilityDate,
        Time startTime,
        Time endTime,
        String modality,
        Long tutor
) {
}
