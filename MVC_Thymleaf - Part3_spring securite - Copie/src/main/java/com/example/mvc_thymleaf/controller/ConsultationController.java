package com.example.mvc_thymleaf.controller;

import com.example.mvc_thymleaf.Models.Consultation;
import com.example.mvc_thymleaf.Models.Medecin;
import com.example.mvc_thymleaf.repo.ConsultationRepo;

import com.example.mvc_thymleaf.repo.RendezvousRepo;
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

import javax.validation.Valid;
import java.util.List;

@Controller
public class ConsultationController {


    @Autowired
    private ConsultationRepo consultationRepo;
    @Autowired
    private RendezvousRepo rendezvousRepo;


    @GetMapping("/user/consultations")
    @ResponseBody
    public List<Consultation> listConsultation(){
        return  consultationRepo.findAll();
    }

    @GetMapping(path = "/user/consultation")
    public String  consultations(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "10") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Consultation> pageConsultation=consultationRepo.findByRapportContains(keyword, PageRequest.of(page,size));
       // model.addAttribute("rendivous", .getCountries());
        model.addAttribute("listConsultation",pageConsultation.getContent());
        model.addAttribute("pages",new int[pageConsultation.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "Consultation/consultations";
    }



    @GetMapping("/admin/detailconsultation")
    public String detailconsultation(Model model,Long id){
        Consultation consultation=consultationRepo.findById(id).orElse(null);
        if(consultation==null) throw  new RuntimeException("Consultation introuvable");
        model.addAttribute("consultation",consultation);

        return "/Consultation/detailconsultation";

    }

    @GetMapping("/admin/formconsultation")
    public String formconsultation(Model model){
        model.addAttribute("consultation",new Consultation());
        return "/Consultation/formconsultation";
    }

    @PostMapping("/admin/saveconsultation")
    public String save(Model model, @Valid Consultation consultation, BindingResult b
    ){
        if(b.hasErrors()){
            return "/Consultation/formconsultation";
        }
        consultationRepo.save(consultation);
        return "redirect:/user/consultation";
    }


    @GetMapping("/admin/editconsultation")
    public String formconsultation(Model model,Long id,String keyword,int page){
        Consultation consultation=consultationRepo.findById(id).orElse(null);
        if(consultation==null) throw  new RuntimeException("consultation introuvable");
        model.addAttribute("consultation",consultation);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "/Consultation/editconsultation";

    }

    @PostMapping("/admin/savec")
    public String save(Model model, @Valid Consultation consultation, BindingResult b,
                       @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        if(b.hasErrors()){
            return "/Consultation/editconsultation";
        }
        consultationRepo.save(consultation);
        return "redirect:/user/consultations?page="+page+"&keyword="+keyword;
    }




    @GetMapping ("/admin/delete2")
    public String delete(Long id,String keyword,int page) {
        consultationRepo.deleteById(id);
        return "redirect:/user/consultations?page="+page+"&keyword="+keyword;
    }










}
