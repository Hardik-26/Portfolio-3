package com.portfolio.api.service;


import com.portfolio.api.model.Module;
import com.portfolio.api.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository repository;

    public List<Module> findAll() {
        return repository.findAll();
    }

    public Optional<Module> findById(Long id) {
        return repository.findById(id);
    }

    public Module save(Module module) {
        return repository.save(module);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}