package edu.sergijr.microservice.lecturer.mapper;

import edu.sergijr.microservice.lecturer.dto.CreateLecturerDTO;
import edu.sergijr.microservice.lecturer.model.Lecturer;
import edu.sergijr.microservice.lecturer.dto.LecturerDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class LecturerMapper {
    public abstract Lecturer toEntity(CreateLecturerDTO createLecturerDTO);

    public abstract Lecturer toEntity(LecturerDto lecturerDto);

    public abstract LecturerDto toDto(Lecturer lecturer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void partialUpdate(LecturerDto lecturerDto, @MappingTarget Lecturer lecturer);
}
