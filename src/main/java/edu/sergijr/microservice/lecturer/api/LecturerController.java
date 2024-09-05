package edu.sergijr.microservice.lecturer.api;

import com.fasterxml.jackson.databind.JsonNode;
import edu.sergijr.microservice.lecturer.model.LecturerDto;
import edu.sergijr.microservice.lecturer.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lecturers")
@RequiredArgsConstructor
public class LecturerController {

    private final LecturerService lecturerService;

    @GetMapping
    public Page<LecturerDto> getList(Pageable pageable) {
        return lecturerService.getList(pageable);
    }

    @GetMapping("/{id}")
    public LecturerDto getOne(@PathVariable UUID id) {
        return lecturerService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<LecturerDto> getMany(@RequestParam List<UUID> ids) {
        return lecturerService.getMany(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LecturerDto create(@RequestBody LecturerDto dto) {
        return lecturerService.create(dto);
    }

    @PatchMapping("/{id}")
    public LecturerDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return lecturerService.patch(id, patchNode);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LecturerDto delete(@PathVariable UUID id) {
        return lecturerService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMany(@RequestParam List<UUID> ids) {
        lecturerService.deleteMany(ids);
    }
}
