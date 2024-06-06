package com.portfolio.api.service;

import com.portfolio.api.model.Module;
import com.portfolio.api.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public Module createModule(Module data) {
        return moduleRepository.save(data);
    }

    public Optional<Module> getModule(Long id) {
        return moduleRepository.findById(id);
    }

    public Module updateModule(Long id, Module module) {
        module.setId(id);
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
}
