package unimagdalena.edu.tutorials.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unimagdalena.edu.tutorials.dto.JwtResponse;
import unimagdalena.edu.tutorials.dto.LoginRequest;
import unimagdalena.edu.tutorials.dto.SignupRequest;
import unimagdalena.edu.tutorials.dto.UserDTO;
import unimagdalena.edu.tutorials.entity.ERole;
import unimagdalena.edu.tutorials.entity.Role;
import unimagdalena.edu.tutorials.entity.User;
import unimagdalena.edu.tutorials.repositories.RoleRepository;
import unimagdalena.edu.tutorials.repositories.UserRepository;
import unimagdalena.edu.tutorials.security.jwt.JwtUtil;
import unimagdalena.edu.tutorials.security.service.UserDetailsImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth/")
@CrossOrigin(origins = "*") //we have to set this
public class AuthenticationAPI {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(),
                        loginRequest.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateJwtToken(authentication);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(
                role->role.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    private ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        User user = new User();
        user.setFirstname(signupRequest.firstname());
        user.setLastname(signupRequest.lastname());
        user.setEmail(signupRequest.email());
        user.setPassword(passwordEncoder.encode(signupRequest.password()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.valueOf("ROLE_USER")).orElse(null));
        user.setRoles(roles);
        User newUser = userRepository.save(user);

        return ResponseEntity.ok(newUser);
    }
}
