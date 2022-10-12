package com.example.mvc_thymleaf.controller;

import com.example.mvc_thymleaf.Models.Medecin;
import com.example.mvc_thymleaf.Models.Patient;
import com.example.mvc_thymleaf.repo.MedcinRepo;
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
public class MedcinController {

    @Autowired
    private MedcinRepo medcinRepo;


    @GetMapping("/user/medcins")
    @ResponseBody
    public List<Medecin> listMedcins(){
        return  medcinRepo.findAll();
    }

    @GetMapping(path = "/user/medcin")
    public String  medcins(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "10") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Medecin> pageMedcin=medcinRepo.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listMedcins",pageMedcin.getContent());
        model.addAttribute("pages",new int[pageMedcin.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "Medcin/medcins";
    }

    @GetMapping("/admin/detailmedcin")
    public String detailmedcin(Model model,Long id){
        Medecin medcin=medcinRepo.findById(id).orElse(null);
        if(medcin==null) throw  new RuntimeException("Patient introuvable");
        model.addAttribute("medcin",medcin);

        return "/Medcin/detailmedcin";

    }

    @GetMapping("/admin/formmedcin")
    public String formmedcin(Model model){
        model.addAttribute("medcin",new Medecin());
        return "/Medcin/formmedcin";
    }



    @PostMapping("/admin/savemedcin")
    public String save(Model model, @Valid Medecin medcin, BindingResult b
    ){
        if(b.hasErrors()){
            return "/Medcin/formmedcin";
        }
        medcinRepo.save(medcin);
        return "redirect:/user/medcin";
    }


    @GetMapping("/admin/editmedcin")
    public String formmedcin(Model model,Long id,String keyword,int page){
        Medecin medcin=medcinRepo.findById(id).orElse(null);
        if(medcin==null) throw  new RuntimeException("medcin introuvable");
        model.addAttribute("medcin",medcin);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "/Medcin/editmedcin";

    }

    @PostMapping("/admin/savem")
    public String save(Model model, @Valid Medecin medecin, BindingResult b,
                       @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        if(b.hasErrors()){
            return "/Medcin/editmedcin";
        }
        medcinRepo.save(medecin);
        return "redirect:/user/medcin?page="+page+"&keyword="+keyword;
    }




    @GetMapping ("/admin/delete1")
    public String delete(Long id,String keyword,int page) {
        medcinRepo.deleteById(id);
        return "redirect:/user/medcin?page="+page+"&keyword="+keyword;
    }













}
