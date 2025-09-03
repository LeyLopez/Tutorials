package unimagdalena.edu.tutorials.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Override
    public Optional<RoleDTO> findRoleById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<RoleDTO> findRoleByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        return List.of();
    }

    @Override
    public void deleteRoleById(Long id) {

    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public Optional<RoleDTO> updateRoleById(Long id, RoleDTO roleDTO) {
        return Optional.empty();
    }
}
