package com.example.mvc_thymleaf.securite.reposorty;

import com.example.mvc_thymleaf.securite.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String>{


        AppUser findByUsername(String username);
}
