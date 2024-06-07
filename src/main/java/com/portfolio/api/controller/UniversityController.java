package com.portfolio.api.controller;

import com.portfolio.api.model.University;
import com.portfolio.api.service.UniversityService;
import com.portfolio.api.specification.UniversitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping
    public ResponseEntity<University> createUniversity(@RequestBody University university) {
        University createdUniversity = universityService.createUniversity(university);
        return ResponseEntity.ok(createdUniversity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        Optional<University> university = universityService.getUniversity(id);
        return university.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        University updatedUniversity = universityService.updateUniversity(id, university);
        return ResponseEntity.ok(updatedUniversity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<University>> searchUniversities(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String department,
            Pageable pageable) {
        Specification<University> spec = UniversitySpecification.getUniversitySpecification(name, country, department);
        Page<University> universities = universityService.searchUniversities(spec, pageable);
        return ResponseEntity.ok(universities);
    }
}
