package unimagdalena.edu.tutorials.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unimagdalena.edu.tutorials.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDto(Role role);

    @Mapping(target = "role.roleId", ignore = true)
    RoleDTO toDtoWithoutId(Role role);

    Role toEntity(RoleDTO roleDTO);
}
