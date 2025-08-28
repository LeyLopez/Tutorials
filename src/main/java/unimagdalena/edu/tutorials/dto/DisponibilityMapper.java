package unimagdalena.edu.tutorials.dto;


import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import unimagdalena.edu.tutorials.entity.Disponibility;
import unimagdalena.edu.tutorials.entity.User;
import unimagdalena.edu.tutorials.service.UserService;

@Mapper(componentModel = "spring")
public interface DisponibilityMapper {

    @Mapping(source = "tutor.userId", target = "tutor")
    DisponibilityDTO toDto(Disponibility disponibility);

    @Mapping(source = "tutor.userId", target = "tutor")
    @Mapping(target = "disponibilityId", ignore = true)
    DisponibilityDTO toDtoWithoutId(Disponibility disponibility);

    @Mapping(source = "tutor", target = "tutor", qualifiedByName = "IdToUser")
    Disponibility toEntity(DisponibilityDTO disponibilityDTO, @Context UserService userService);

    @Named("IdToUser")
    default User mapIdToUser(Long userId, @Context UserService userService){
        return userId != null ? userService.findUserById(userId) : null;
    }


}
