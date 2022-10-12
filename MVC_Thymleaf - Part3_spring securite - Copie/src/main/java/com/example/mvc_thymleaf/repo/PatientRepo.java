package com.example.mvc_thymleaf.repo;

import com.example.mvc_thymleaf.Models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    //Page<Patient> findByNomContains(String key, Pageable pageable);

    //Page<Patient> findByNomAnAndScoor(String key,int sco,Pageable pageable);

    //Page<Patient> findByNomAndScoor(String key,int s, Pageable pageable);

    //Page<Patient> findByNomContainsOrScoorOrMalade(String key,int b,int s, Pageable pageable);


        Page<Patient> findByNomContains(String key, Pageable pageable);

}
