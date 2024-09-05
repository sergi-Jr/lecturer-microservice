package edu.sergijr.microservice.lecturer.model;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link Lecturer}
 */
public record LecturerDto(UUID id, String firstName, Instant createdDate, Instant lastModifiedDate, String lastName,
                          String about) {
}