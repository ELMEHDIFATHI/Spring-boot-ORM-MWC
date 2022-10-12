package com.example.mvc_thymleaf.repo;

import com.example.mvc_thymleaf.Models.Medecin;
import com.example.mvc_thymleaf.Models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedcinRepo extends JpaRepository<Medecin,Long> {

    Page<Medecin> findByNomContains(String key, Pageable pageable);
}
