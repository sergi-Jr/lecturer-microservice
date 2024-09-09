package edu.sergijr.microservice.lecturer.api;

import com.fasterxml.jackson.databind.JsonNode;
import edu.sergijr.microservice.lecturer.dto.CreateLecturerDTO;
import edu.sergijr.microservice.lecturer.dto.LecturerDto;
import edu.sergijr.microservice.lecturer.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/lecturers", produces = {"application/json"})
@RequiredArgsConstructor
public class LecturerController {

    private final LecturerService lecturerService;

    @GetMapping
    public CollectionModel<LecturerDto> getList(Pageable pageable) {
        Page<LecturerDto> lecturers = lecturerService.getList(pageable);
        List<Link> dtoLinks = new LinkedList<>();
        lecturers.forEach(dto -> {
            Link getOneLink = linkTo(LecturerController.class).slash(dto.getId()).withRel("getOne");
            Link deleteOneLink = linkTo(LecturerController.class).slash(dto.getId()).withRel("deleteOne");
            dtoLinks.add(getOneLink);
            dtoLinks.add(deleteOneLink);
            dto.add(dtoLinks);
        });
        Link selfLink = linkTo(methodOn(LecturerController.class).getList(pageable)).withSelfRel();
        return CollectionModel.of(lecturers, selfLink);
    }

    @GetMapping("/{id}")
    public LecturerDto getOne(@PathVariable UUID id) {
        LecturerDto lecturer = lecturerService.getOne(id);
        List<Link> dtoLinks = new LinkedList<>();
        Link getOneLink = linkTo(LecturerController.class).slash(lecturer.getId()).withSelfRel();
        Link deleteOneLink = linkTo(LecturerController.class).slash(lecturer.getId()).withRel("deleteOne");
        dtoLinks.add(getOneLink);
        dtoLinks.add(deleteOneLink);
        return lecturer.add(dtoLinks);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LecturerDto create(@RequestBody CreateLecturerDTO dto) {
        LecturerDto lecturer = lecturerService.create(dto);
        List<Link> dtoLinks = new LinkedList<>();
        Link getOneLink = linkTo(LecturerController.class).slash(lecturer.getId()).withRel("getOne");
        Link deleteOneLink = linkTo(LecturerController.class).slash(lecturer.getId()).withRel("deleteOne");
        dtoLinks.add(getOneLink);
        dtoLinks.add(deleteOneLink);
        return lecturer.add(dtoLinks);
    }

    @PatchMapping("/{id}")
    public LecturerDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        LecturerDto lecturer = lecturerService.patch(id, patchNode);
        List<Link> dtoLinks = new LinkedList<>();
        Link getOneLink = linkTo(LecturerController.class).slash(lecturer.getId()).withRel("getOne");
        Link deleteOneLink = linkTo(LecturerController.class).slash(lecturer.getId()).withRel("deleteOne");
        dtoLinks.add(getOneLink);
        dtoLinks.add(deleteOneLink);
        return lecturer.add(dtoLinks);
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
