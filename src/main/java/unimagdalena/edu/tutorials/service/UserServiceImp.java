package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Override
    public List<UserDTO> findAllUsers() {
        return List.of();
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Optional<UserDTO> updateUserById(Long id, UserDTO userDTO) {
        return Optional.empty();
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
