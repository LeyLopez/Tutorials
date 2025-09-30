package unimagdalena.edu.tutorials.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unimagdalena.edu.tutorials.dto.UserDTO;
import unimagdalena.edu.tutorials.exception.NotFoundException;
import unimagdalena.edu.tutorials.security.service.UserService;



import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    private final UserService userService;


    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        return userService.findUserById(id).map(
                user->ResponseEntity.ok().body(user))
                .orElseThrow(()-> new NotFoundException("The user with id " + id + " does not exist."));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createdUser(@RequestBody UserDTO userDTO){
        return createUser(userDTO);
    }

    private ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        UserDTO newUser = userService.saveUser(userDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.userId()).toUri();

        return ResponseEntity.created(location).body(newUser);
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        Optional<UserDTO> userToUpdate = userService.updateUserById(id, userDTO);
        return userToUpdate
                .map(user->ResponseEntity.ok().body(user))
                .orElseGet(()-> {return createUser(userDTO);});
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id){
        return userService.findUserById(id).map(user->{
            userService.deleteUserById(id);

            return ResponseEntity.ok().body(user);
        }).orElseThrow(()-> new NotFoundException("The user with id " + id + " does not exist."));
    }
}
