package unimagdalena.edu.tutorials.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unimagdalena.edu.tutorials.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO toDTO(Subject subject);

    @Mapping(target = "subject.subjectId", ignore = true)
    SubjectDTO toDtoWithoutId(Subject subject);

    Subject toEntity(SubjectDTO subjectDTO);
}
