package com.portfolio.api.assembler;

import com.portfolio.api.controller.UniversityController;
import com.portfolio.api.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UniversityAssembler implements RepresentationModelAssembler<University, EntityModel<University>> {

    @Override
    public EntityModel<University> toModel(University university) {
        return EntityModel.of(university,
                linkTo(methodOn(UniversityController.class).getUniversity(university.getId())).withSelfRel(),
                linkTo(methodOn(UniversityController.class).searchUniversities(null, null,null,null)).withRel("universities"));
    }

    public PagedModel<EntityModel<University>> toPagedModel(Page<University> universities, Pageable pageable) {
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(universities.getSize(), pageable.getPageNumber(), pageable.getPageSize(), universities.getTotalElements());
        PagedModel<EntityModel<University>> pagedModel = PagedModel.of(universities.stream().map(this::toModel).collect(Collectors.toList()), metadata);
        pagedModel.add(linkTo(methodOn(UniversityController.class).searchUniversities(null, null,null,null)).withRel("universities"));
        return pagedModel;
    }

}