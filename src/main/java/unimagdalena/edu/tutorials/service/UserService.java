package unimagdalena.edu.tutorials.service;

import unimagdalena.edu.tutorials.dto.UserDTO;
import unimagdalena.edu.tutorials.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAllUsers();
    Optional<UserDTO> findUserById(Long id);
    Optional<UserDTO> findUserByEmail(String email);
    UserDTO saveUser(UserDTO userDTO);
    Optional<UserDTO> updateUserById(Long id, UserDTO userDTO);
    void deleteUserById(Long id);
}
