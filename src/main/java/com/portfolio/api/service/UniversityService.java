package com.portfolio.api.service;


import com.portfolio.api.model.University;
import com.portfolio.api.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    public Optional<University> getUniversity(Long id) {
        return universityRepository.findById(id);
    }

    public University updateUniversity(Long id, University university) {
        university.setId(id);
        return universityRepository.save(university);
    }

    public void deleteUniversity(Long id) {
        universityRepository.deleteById(id);
    }

    public Page<University> searchUniversities(Specification<University> spec, Pageable pageable) {
        return universityRepository.findAll(spec, pageable);
    }
}
