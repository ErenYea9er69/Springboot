package com.example.pc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;
import com.example.pc.service.PcService;

@Controller
public class PcControllers {

    @Autowired
    PcService pcService;

    @RequestMapping("/listePcs")
    public String listePcs(ModelMap modelMap,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Pc> prods = pcService.getAllPcsParPage(page, size);
        modelMap.addAttribute("pcs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listePcs";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<Style> styles = pcService.getAllStyles(); // [cite: 326]
        modelMap.addAttribute("pc", new Pc()); // [cite: 327]
        modelMap.addAttribute("mode", "new"); // [cite: 328]
        modelMap.addAttribute("styles", styles); // [cite: 329]
        return "formPc"; // [cite: 330]
    }

    @RequestMapping("/savePc")
    public String savePc(@Valid Pc pc, BindingResult bindingResult, // [cite: 359]
                         @RequestParam(name = "page", defaultValue = "0") int page, // [cite: 360]
                         @RequestParam(name = "size", defaultValue = "2") int size, // [cite: 361]
                         ModelMap modelMap) {
        
        int currentPage; // [cite: 363]
        boolean isNew = false; // [cite: 364]
        
        if (bindingResult.hasErrors()) {
            // Re-fetch styles if validation fails so the dropdown populates
            modelMap.addAttribute("styles", pcService.getAllStyles()); 
            return "formPc"; // [cite: 365]
        }
        
        if (pc.getIdPc() == null) // [cite: 366]
            isNew = true; // [cite: 367]
            
        pcService.savePc(pc); // [cite: 368]
        
        if (isNew) { // [cite: 369]
            Page<Pc> prods = pcService.getAllPcsParPage(page, size); // [cite: 371]
            currentPage = prods.getTotalPages() - 1; // [cite: 372]
        } else {
            currentPage = page; // [cite: 374]
        }
        
        return ("redirect:/listePcs?page=" + currentPage + "&size=" + size); // [cite: 375]
    }

    @RequestMapping("/modifierPc")
    public String editerPc(@RequestParam("id") Long id, ModelMap modelMap,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "2") int size) {
        Pc p = pcService.getPc(id); // [cite: 335]
        List<Style> styles = pcService.getAllStyles(); // [cite: 336]
        
        modelMap.addAttribute("pc", p); // [cite: 337]
        modelMap.addAttribute("mode", "edit"); // [cite: 338]
        modelMap.addAttribute("styles", styles); // [cite: 339]
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);
        
        return "formPc"; // [cite: 340]
    }

    @RequestMapping("/supprimerPc")
    public String supprimerPc(@RequestParam("id") Long id, ModelMap modelMap,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "2") int size) {
        pcService.deletePcById(id);
        Page<Pc> prods = pcService.getAllPcsParPage(page, size);
        modelMap.addAttribute("pcs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listePcs";
    }
}