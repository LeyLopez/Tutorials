package unimagdalena.edu.tutorials.dto;

import java.sql.Time;
import java.time.LocalDate;

public record MentoryDTO(
        Long mentoryId,
        Long tutor,
        Long subject,
        LocalDate mentoryDate,
        Time startTime,
        Time endTime,
        String modality,
        Integer maxCapacity,
        Integer availableCapacity,
        String status
) {
}
