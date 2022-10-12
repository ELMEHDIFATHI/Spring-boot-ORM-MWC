package com.example.mvc_thymleaf.securite.services;

import com.example.mvc_thymleaf.securite.models.AppRole;
import com.example.mvc_thymleaf.securite.models.AppUser;
import com.example.mvc_thymleaf.securite.reposorty.AppRoleRepository;
import com.example.mvc_thymleaf.securite.reposorty.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class securityimpl implements  SecurityServices {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Pawwords not match");
        String hashedPwd=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword (hashedPwd);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
       // AppRole appRole =appRoleRepository.findByroleName(roleName);
        AppRole appRole=appRoleRepository.findByroleName(roleName);
        if(appRole!=null) throw new RuntimeException ("Role "+roleName+" already exist");
        appRole=new AppRole();
        appRole.setRoleName (roleName);
        appRole.setDescription(description);
        AppRole app=appRoleRepository.save (appRole);
        return app;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appuser=appUserRepository.findByUsername (username);
        if(appuser==null) throw new RuntimeException("Role "+roleName+" already exist");
        AppRole appRole=appRoleRepository.findByroleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appuser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appuser=appUserRepository.findByUsername (username);
        if(appuser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByroleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appuser.getAppRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }
}
