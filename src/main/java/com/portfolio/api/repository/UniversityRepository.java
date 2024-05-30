package com.portfolio.api.repository;

import com.portfolio.api.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UniversityRepository extends JpaRepository<University, Long>, JpaSpecificationExecutor<University> {

}
