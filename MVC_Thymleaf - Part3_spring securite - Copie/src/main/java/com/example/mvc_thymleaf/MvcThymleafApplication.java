package com.example.mvc_thymleaf;

import com.example.mvc_thymleaf.Models.Patient;
import com.example.mvc_thymleaf.repo.PatientRepo;
import com.example.mvc_thymleaf.securite.services.SecurityServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MvcThymleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcThymleafApplication.class, args);
    }






      @Bean

        CommandLineRunner commandLineRunner(PatientRepo patientRepository){
        return args->{
            patientRepository.save(
                    new Patient( null,"Hassan", new Date(),  false,111));
            patientRepository.save(
                    new Patient(  null, "Mohammed", new Date(),  true,210));
            patientRepository.save(
                    new Patient( null,"Yasmine", new Date(),  false,150 ));
            patientRepository.save(
                    new Patient(  null, "Hanae", new Date(),  false,1811 ));


            patientRepository.findAll(). forEach(p->{
                System.out.println(p.getNom());
            });

        };

      }

  // @Bean
    CommandLineRunner saveUsers(SecurityServices securityService){
        return args -> {
            securityService.saveNewUser(  "mohamed", "1234",  "1234");
            securityService.saveNewUser(  "yasmine",  "1234",  "1234");
            securityService.saveNewUser(  "hassan",  "1234",  "1234");

            securityService.saveNewRole( "USER",  "");
            securityService.saveNewRole( "ADMIN",  "");

            securityService.addRoleToUser(  "mohamed",  "USER");
            securityService.addRoleToUser(  "mohamed", "ADMIN");
            securityService.addRoleToUser(  "yasmine",  "USER");
            securityService.addRoleToUser(  "hassan",  "USER");

        };

    }
}
