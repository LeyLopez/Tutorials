package unimagdalena.edu.tutorials.service;

import unimagdalena.edu.tutorials.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<RoleDTO> findRoleById(Long id);
    Optional<RoleDTO> findRoleByName(String name);
    List<RoleDTO> findAllRoles();
    void deleteRoleById(Long id);
    RoleDTO saveRole(RoleDTO roleDTO);
    Optional<RoleDTO> updateRoleById(Long id, RoleDTO roleDTO);
}
