package com.portfolio.api.service;


import com.portfolio.api.model.University;
import com.portfolio.api.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    public Page<University> searchUniversities(String name, String country, String department, Pageable pageable) {
        Specification<University> spec = Specification.where(null);

        if (StringUtils.hasText(name)) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (StringUtils.hasText(country)) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), country));
        }
        if (StringUtils.hasText(department)) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("department"), department));
        }

        return universityRepository.findAll(spec, pageable);
    }
}
