package unimagdalena.edu.tutorials.security.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.UserDTO;
import unimagdalena.edu.tutorials.dto.UserMapper;
import unimagdalena.edu.tutorials.entity.User;
import unimagdalena.edu.tutorials.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).map(userMapper::toDto);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User newUser = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(newUser);
    }

    @Override
    public Optional<UserDTO> updateUserById(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(
                userInBD->{
                    userInBD.setFirstname(userDTO.firstname());
                    userInBD.setLastname(userDTO.lastname());
                    userInBD.setEmail(userDTO.email());
                    userInBD.setPassword(userDTO.password());


                    return userRepository.save(userMapper.toEntity(userDTO));
                }
        ).map(userMapper::toDto);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
