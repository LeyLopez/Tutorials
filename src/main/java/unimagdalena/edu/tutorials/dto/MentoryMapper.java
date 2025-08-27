package unimagdalena.edu.tutorials.dto;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import unimagdalena.edu.tutorials.entity.Mentory;
import unimagdalena.edu.tutorials.entity.Subject;
import unimagdalena.edu.tutorials.entity.User;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface MentoryMapper {

    @Mapping(source = "tutor.userId", target = "tutor")
    @Mapping(source = "subject.subjectId", target = "subject")
    MentoryDTO toDto(Mentory mentory);

    @Mapping(source = "subject.subjectId", target = "subject")
    @Mapping(source = "tutor.userId", target = "tutor")
    @Mapping(target = "mentoryId", ignore = true)
    MentoryDTO toDtoWithoutId(Mentory mentory);

    @Mapping(source = "subject.subjectId", target = "subject", qualifiedByName = "IdToSubject")
    @Mapping(source = "tutor.userId", target = "tutor", qualifiedByName = "IdToUser")
    Mentory toEntity(MentoryDTO mentoryDTO, @Context UserService userService, @Context SubjectService subjectService);

    @Named("IdToUser")
    default User mapIdToUser(Long userId, @Context UserService userService){
        return userId != null ? userService.findUserById(userId) : null;
    }

    @Named("IdToSubject")
    default Subject mapIdToSubject(Long subjectId, @Context SubjectService subjectService){
        return subjectId != null ? subjectService.findSubjectById(subjectId) : null;
    }

}
