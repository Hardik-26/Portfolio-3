package com.portfolio.api.controller;


import com.portfolio.api.model.PartnerUniversity;
import com.portfolio.api.service.PartnerUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/universities")
public class PartnerUniversityController {
    @Autowired
    private PartnerUniversityService service;

    @GetMapping
    public CollectionModel<EntityModel<PartnerUniversity>> getAll() {
        List<PartnerUniversity> universities = service.findAll();
        List<EntityModel<PartnerUniversity>> universityModels = universities.stream()
                .map(university -> EntityModel.of(university,
                        WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getById(university.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getAll()).withRel("universities")))
                .toList();

        return CollectionModel.of(universityModels,
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PartnerUniversity>> getById(@PathVariable Long id) {
        Optional<PartnerUniversity> universityOpt = service.findById(id);
        if (universityOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PartnerUniversity university = universityOpt.get();
        EntityModel<PartnerUniversity> universityModel = EntityModel.of(university,
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getAll()).withRel("universities"));

        return ResponseEntity.ok(universityModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<PartnerUniversity>> create(@RequestBody PartnerUniversity university) {
        PartnerUniversity createdUniversity = service.save(university);
        EntityModel<PartnerUniversity> universityModel = EntityModel.of(createdUniversity,
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getById(createdUniversity.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getAll()).withRel("universities"));

        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getById(createdUniversity.getId())).toUri())
                .body(universityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PartnerUniversity>> update(@PathVariable Long id, @RequestBody PartnerUniversity university) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        university.setId(id);
        PartnerUniversity updatedUniversity = service.save(university);
        EntityModel<PartnerUniversity> universityModel = EntityModel.of(updatedUniversity,
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getById(updatedUniversity.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PartnerUniversityController.class).getAll()).withRel("universities"));

        return ResponseEntity.ok(universityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}