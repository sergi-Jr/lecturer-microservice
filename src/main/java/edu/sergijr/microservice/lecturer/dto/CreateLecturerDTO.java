package edu.sergijr.microservice.lecturer.dto;

import edu.sergijr.microservice.lecturer.model.Lecturer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link Lecturer}
 */
public record CreateLecturerDTO(@Size(message = "Must be greater than 1 and less than 32 symbols", min = 1, max = 32)
                                @Pattern(message = "Must starts with capital letter", regexp = "^[A-Z].*")
                                @NotBlank String firstName,
                                @Size(message = "Must be greater than 1 and less than 64 symbols", min = 1, max = 64)
                                @Pattern(message = "Must starts with capital letter", regexp = "^[A-Z].*")
                                @NotBlank String lastName,
                                String about) {
}