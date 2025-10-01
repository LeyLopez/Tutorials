package unimagdalena.edu.tutorials.api;


import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unimagdalena.edu.tutorials.dto.RoleDTO;
import unimagdalena.edu.tutorials.exception.NotFoundException;
import unimagdalena.edu.tutorials.security.service.RoleService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*")
public class RoleAPI {

    private final RoleService roleService;


    public RoleAPI(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
        return roleService.findRoleById(id)
                .map(role->ResponseEntity.ok().body(role))
                .orElseThrow(()->new NotFoundException("The role with the id: "+id+" does not exist"));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createdRole(@RequestBody RoleDTO roleDTO) {
        return createRole(roleDTO);
    }

    private ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO) {
        RoleDTO newRole = roleService.saveRole(roleDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRole.roleId()).toUri();

        return ResponseEntity.created(location).body(newRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        Optional<RoleDTO> roleToUpdate = roleService.updateRoleById(id, roleDTO);
        return roleService.findRoleById(id).map(role->ResponseEntity.ok().body(role))
                .orElseGet(()->{return createRole(roleDTO);});
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoleDTO> deleteRole(@PathVariable Long id) {
        return roleService.findRoleById(id).map(role->{
            roleService.deleteRoleById(id);
            return ResponseEntity.ok().body(role);
        }).orElseThrow(()->new NotFoundException("The role with the id: "+id+" does not exist"));
    }
}
