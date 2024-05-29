package com.portfolio.api.service;


import com.portfolio.api.model.PartnerUniversity;
import com.portfolio.api.repository.PartnerUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerUniversityService {
    @Autowired
    private PartnerUniversityRepository repository;

    public List<PartnerUniversity> findAll() {
        return repository.findAll();
    }

    public Optional<PartnerUniversity> findById(Long id) {
        return repository.findById(id);
    }

    public PartnerUniversity save(PartnerUniversity university) {
        return repository.save(university);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}