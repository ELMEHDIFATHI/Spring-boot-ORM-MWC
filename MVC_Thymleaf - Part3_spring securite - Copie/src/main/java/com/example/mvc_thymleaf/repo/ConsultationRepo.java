package com.example.mvc_thymleaf.repo;

import com.example.mvc_thymleaf.Models.Consultation;
import com.example.mvc_thymleaf.Models.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepo extends JpaRepository<Consultation,Long> {


    Page<Consultation> findByRapportContains(String key, Pageable pageable);
}
