package unimagdalena.edu.tutorials.dto;

import java.time.LocalDate;

public record RegistrationDTO(
        Long registrationId,
        LocalDate registrationDate,
        String status,
        Long student,
        Long mentory
) {
}
