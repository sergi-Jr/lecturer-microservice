package edu.sergijr.microservice.lecturer.repository;

import edu.sergijr.microservice.lecturer.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LecturerRepository extends JpaRepository<Lecturer, UUID> {
}
