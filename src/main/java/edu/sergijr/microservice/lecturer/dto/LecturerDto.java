package edu.sergijr.microservice.lecturer.dto;

import edu.sergijr.microservice.lecturer.model.Lecturer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
public class LecturerDto extends RepresentationModel<LecturerDto> {
    private UUID id;
    private String firstName;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String lastName;
    private String about;


}