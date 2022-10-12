package com.example.mvc_thymleaf.securite.services;

import com.example.mvc_thymleaf.securite.models.AppRole;
import com.example.mvc_thymleaf.securite.models.AppUser;

public interface SecurityServices {

    AppUser saveNewUser (String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser (String username, String roleName);
    AppUser loadUserByUserName (String username);
}
