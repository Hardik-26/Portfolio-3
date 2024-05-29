package com.portfolio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.api.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

}