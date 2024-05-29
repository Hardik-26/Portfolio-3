package com.portfolio.api.controller;

import com.portfolio.api.model.Module;
import com.portfolio.api.service.ModuleService;
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
@RequestMapping("/modules")
public class ModuleController {
    @Autowired
    private ModuleService service;

    @GetMapping
    public CollectionModel<EntityModel<Module>> getAll() {
        List<Module> modules = service.findAll();
        List<EntityModel<Module>> moduleModels = modules.stream()
                .map(module -> EntityModel.of(module,
                        WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getById(module.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getAll()).withRel("modules")))
                .toList();

        return CollectionModel.of(moduleModels,
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Module>> getById(@PathVariable Long id) {
        Optional<Module> moduleOpt = service.findById(id);
        if (moduleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Module module = moduleOpt.get();
        EntityModel<Module> moduleModel = EntityModel.of(module,
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getAll()).withRel("modules"));

        return ResponseEntity.ok(moduleModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Module>> create(@RequestBody Module module) {
        Module createdModule = service.save(module);
        EntityModel<Module> moduleModel = EntityModel.of(createdModule,
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getById(createdModule.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getAll()).withRel("modules"));

        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getById(createdModule.getId())).toUri())
                .body(moduleModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Module>> update(@PathVariable Long id, @RequestBody Module module) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        module.setId(id);
        Module updatedModule = service.save(module);
        EntityModel<Module> moduleModel = EntityModel.of(updatedModule,
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getById(updatedModule.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).getAll()).withRel("modules"));

        return ResponseEntity.ok(moduleModel);
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