package com.example.mvc_thymleaf.repo;

import com.example.mvc_thymleaf.Models.Rendezvous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezvousRepo extends JpaRepository<Rendezvous,Long> {


    Page<Rendezvous> findByRdvIsStartingWith(String key, Pageable pageable);

}
