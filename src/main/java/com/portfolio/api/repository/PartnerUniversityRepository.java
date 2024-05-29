package com.portfolio.api.repository;


import com.portfolio.api.model.PartnerUniversity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerUniversityRepository extends JpaRepository<PartnerUniversity, Long> {

}