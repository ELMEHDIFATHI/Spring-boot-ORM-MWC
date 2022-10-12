package com.example.mvc_thymleaf.securite.reposorty;

import com.example.mvc_thymleaf.securite.models.AppRole;
import com.example.mvc_thymleaf.securite.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long>{


        AppRole findByroleName(String roleName);

}
