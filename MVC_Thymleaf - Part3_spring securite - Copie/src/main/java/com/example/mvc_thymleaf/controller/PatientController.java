package com.example.mvc_thymleaf.controller;

import com.example.mvc_thymleaf.Models.Patient;
import com.example.mvc_thymleaf.repo.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Binding;
import javax.validation.Valid;
import java.security.Key;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepo patientRepo;


    @GetMapping ("/")
    public String home() {

        return "redirect:/user/index";
    }

    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return  patientRepo.findAll();
    }

    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "10") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword
            ){
        Page<Patient> pagePatients=patientRepo.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);

        return "patients";
        //return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }



    @GetMapping ("/admin/delete")
    public String delete(Long id,String keyword,int page) {
        patientRepo.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }



    @GetMapping("/admin/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());

        return "formPatients";

    }
    @GetMapping("/admin/detailPatient")
    public String detailPatient(Model model,Long id){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient==null) throw  new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);

        return "detailPatient";

    }



    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult b,
     @RequestParam(name = "page",defaultValue = "0") int page,
    @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
     if(b.hasErrors()){
         return "formPatients";
     }
        patientRepo.save(patient);
                return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @PostMapping("/admin/save1")
    public String save(Model model, @Valid Patient patient, BindingResult b
    ){
        if(b.hasErrors()){
            return "formPatients";
        }
        patientRepo.save(patient);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/editPatient")
    public String formPatient(Model model,Long id,String keyword,int page){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient==null) throw  new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";

    }






}
