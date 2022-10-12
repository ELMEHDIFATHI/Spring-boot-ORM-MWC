package com.example.mvc_thymleaf.controller;

import com.example.mvc_thymleaf.Models.Consultation;
import com.example.mvc_thymleaf.Models.Medecin;
import com.example.mvc_thymleaf.Models.Patient;
import com.example.mvc_thymleaf.Models.Rendezvous;
import com.example.mvc_thymleaf.repo.MedcinRepo;
import com.example.mvc_thymleaf.repo.PatientRepo;
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
public class RendezvousController {

    @Autowired
    private RendezvousRepo rendezvousRepo;
    @Autowired
    private MedcinRepo medcinRepo;
    @Autowired
    private PatientRepo patientRepo;


    @GetMapping("/user/rendivous")
    @ResponseBody
    public List<Rendezvous> listRendezvous(){
        return  rendezvousRepo.findAll();
    }

    @GetMapping(path = "/user/rendivouss")
    public String  rendivouss(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "10") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Rendezvous> pageMedcin=rendezvousRepo.findByRdvIsStartingWith(keyword, PageRequest.of(page,size));

        model.addAttribute("listRendezvous",pageMedcin.getContent());
        model.addAttribute("pages",new int[pageMedcin.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "RendezVous/Rendezvouse";
    }

    @GetMapping ("/admin/deleterendivous")
    public String deleterendivous(Long id,String keyword,int page) {
        rendezvousRepo.deleteById(id);
        return "redirect:/user/rendivouss?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/detailRendezvous")
    public String detailRendezvous(Model model,Long id){
        Rendezvous rendezvous=rendezvousRepo.findById(id).orElse(null);
        if(rendezvous==null) throw  new RuntimeException("rendezvous introuvable");
        model.addAttribute("rendezvous",rendezvous);

        return "RendezVous/detailRendezvous";

    }

    @GetMapping("/admin/formRendezvous")
    public String formRendezvous(Model model){
        model.addAttribute("rendezvous",new Rendezvous());
        List<Medecin> medecinList= medcinRepo.findAll();
        List<Patient> patientList= patientRepo.findAll();
        model.addAttribute("listMedcins",medecinList);
        model.addAttribute("listPatient", patientList);
        return "/RendezVous/formRendezvous";
    }

    @PostMapping("/admin/saverendezvous")
    public String save(Model model, @Valid Rendezvous rendezvous, BindingResult b
    ){
        if(b.hasErrors()){
            return "/RendezVous/formRendezvous";
        }
        rendezvousRepo.save(rendezvous);
        return "redirect:/user/rendivouss";
    }

    @PostMapping("/admin/saveR")
    public String saveR(Model model, @Valid Rendezvous rendezvous, BindingResult b){
        if(b.hasErrors()){
            return "/RendezVous/editR";
        }
        rendezvousRepo.save(rendezvous);
        return "redirect:/user/rendivouss";
    }

    @GetMapping("/admin/editRendez")
    public String editR(Model model,Long id,String keyword,int page){
        Rendezvous rendezvous=rendezvousRepo.findById(id).orElse(null);
        if(rendezvous==null) throw  new RuntimeException("rendezvous introuvable");
        List<Medecin> medecinList= medcinRepo.findAll();
        List<Patient> patientList= patientRepo.findAll();
        model.addAttribute("listMedcins",medecinList);
        model.addAttribute("listPatient", patientList);
        model.addAttribute("rendezvous",rendezvous);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "/RendezVous/editR";

    }


}
