package edu.sergijr.microservice.lecturer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sergijr.microservice.lecturer.dto.CreateLecturerDTO;
import edu.sergijr.microservice.lecturer.mapper.LecturerMapper;
import edu.sergijr.microservice.lecturer.model.Lecturer;
import edu.sergijr.microservice.lecturer.dto.LecturerDto;
import edu.sergijr.microservice.lecturer.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LecturerService {

    private final LecturerMapper lecturerMapper;

    private final LecturerRepository lecturerRepository;

    private final ObjectMapper objectMapper;

    public Page<LecturerDto> getList(Pageable pageable) {
        Page<Lecturer> lecturers = lecturerRepository.findAll(pageable);
        return lecturers.map(lecturerMapper::toDto);
    }

    public LecturerDto getOne(UUID id) {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(id);
        return lecturerMapper.toDto(lecturerOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<LecturerDto> getMany(List<UUID> ids) {
        List<Lecturer> lecturers = lecturerRepository.findAllById(ids);
        return lecturers.stream()
                .map(lecturerMapper::toDto)
                .toList();
    }

    @Transactional
    public LecturerDto create(CreateLecturerDTO dto) {
        Lecturer lecturer = lecturerMapper.toEntity(dto);
        Lecturer resultLecturer = lecturerRepository.save(lecturer);
        return lecturerMapper.toDto(resultLecturer);
    }

    @Transactional
    public LecturerDto patch(UUID id, JsonNode patchNode) throws IOException {
        Lecturer lecturer = lecturerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        LecturerDto lecturerDto = lecturerMapper.toDto(lecturer);
        objectMapper.readerForUpdating(lecturerDto).readValue(patchNode);
        lecturerMapper.partialUpdate(lecturerDto, lecturer);

        Lecturer resultLecturer = lecturerRepository.save(lecturer);
        return lecturerMapper.toDto(resultLecturer);
    }

    @Transactional
    public LecturerDto delete(UUID id) {
        Lecturer lecturer = lecturerRepository.findById(id).orElse(null);
        if (lecturer != null) {
            lecturerRepository.delete(lecturer);
        }
        return lecturerMapper.toDto(lecturer);
    }

    @Transactional
    public void deleteMany(List<UUID> ids) {
        lecturerRepository.deleteAllById(ids);
    }
}
