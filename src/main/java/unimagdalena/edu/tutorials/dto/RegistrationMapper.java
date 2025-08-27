package unimagdalena.edu.tutorials.dto;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import unimagdalena.edu.tutorials.entity.Mentory;
import unimagdalena.edu.tutorials.entity.Registration;
import unimagdalena.edu.tutorials.entity.User;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(source = "mentory.mentoryId", target = "mentory")
    @Mapping(source = "student.studentId", target = "student")
    RegistrationDTO toDto(Registration registration);

    @Mapping(source = "mentory.mentoryId", target = "mentory")
    @Mapping(source = "student.studentId", target = "student")
    @Mapping(target = "registration.registrationId", ignore = true)
    RegistrationDTO toDtoWithoutId(Registration registration);

    @Mapping(source = "mentory.mentoryId", target = "mentory", qualifiedByName = "IdToMentory")
    @Mapping(source = "student.studentId", target = "student", qualifiedByName = "IdToStudent")
    Registration toEntity(RegistrationDTO registrationDTO, @Context UserService userService, @Context MentoryService mentoryService);


    @Named("IdToMentory")
    default Mentory mapIdToMentory(Long mentoryId, @Context MentoryService mentoryService) {
        return mentoryId != null ? mentoryService.findMentoryById(mentoryId) : null;
    }

    @Named("IdToStudent")
    default User mapIdToStudent(Long userId, @Context UserService userService){
        return userId != null ? userService.findUserById(userId) : null;
    }

}
