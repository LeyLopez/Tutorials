package unimagdalena.edu.tutorials.security.service;

import org.springframework.stereotype.Service;
import unimagdalena.edu.tutorials.dto.RoleDTO;
import unimagdalena.edu.tutorials.dto.RoleMapper;
import unimagdalena.edu.tutorials.entity.ERole;
import unimagdalena.edu.tutorials.entity.Role;
import unimagdalena.edu.tutorials.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImp(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    @Override
    public Optional<RoleDTO> findRoleById(Long id) {
        return roleRepository.findById(id).map(roleMapper::toDto);
    }

    @Override
    public Optional<RoleDTO> findRoleByName(ERole name) {
        return roleRepository.findByName(name).map(roleMapper::toDto);
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = roleRepository.save(roleMapper.toEntity(roleDTO));
        return roleMapper.toDto(role);
    }

    @Override
    public Optional<RoleDTO> updateRoleById(Long id, RoleDTO roleDTO) {
        return roleRepository.findById(id).map(
                roleInBD->{
                    roleInBD.setName(ERole.valueOf(roleDTO.name()));

                    return roleRepository.save(roleInBD);
                }
        ).map(roleMapper::toDto);
    }
}
