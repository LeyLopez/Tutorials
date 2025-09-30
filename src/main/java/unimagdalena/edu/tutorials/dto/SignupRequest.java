package unimagdalena.edu.tutorials.dto;

public record SignupRequest(
        Long userId,
        String firstname,
        String lastname,
        String email,
        String password
) {
}
