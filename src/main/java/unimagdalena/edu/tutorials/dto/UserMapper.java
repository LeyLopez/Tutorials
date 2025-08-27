package unimagdalena.edu.tutorials.dto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unimagdalena.edu.tutorials.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    @Mapping(target = "user.userId", ignore = true)
    UserDTO toDtoWithoutId(User user);

    User toEntity(UserDTO userDTO);
}
