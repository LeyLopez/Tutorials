package unimagdalena.edu.tutorials.dto;

public record UserDTO(Long userId,
                      String firstname,
                      String lastname,
                      String email,
                      String password) {
}
