package com.portfolio.api.controller;

import com.portfolio.api.model.University;
import com.portfolio.api.service.UniversityService;
import com.portfolio.api.assembler.UniversityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityAssembler assembler;

    @PostMapping
    public ResponseEntity<EntityModel<University>> createUniversity(@RequestBody University university) {
        University createdUniversity = universityService.createUniversity(university);
        return ResponseEntity.ok(assembler.toModel(createdUniversity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<University>> getUniversity(@PathVariable Long id) {
        Optional<University> university = universityService.getUniversity(id);
        return university.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<University>> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        University updatedUniversity = universityService.updateUniversity(id, university);
        return ResponseEntity.ok(assembler.toModel(updatedUniversity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<University>>> searchUniversities(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "department", required = false) String department, Pageable pageable) {
        Page<University> universities = universityService.searchUniversities(name, country, department, pageable);
        PagedModel<EntityModel<University>> pagedModel = assembler.toPagedModel(universities, pageable);
        pagedModel.add(linkTo(methodOn(UniversityController.class).searchUniversities(name, country, department, pageable)).withSelfRel());
        return ResponseEntity.ok(pagedModel);
    }
}