package com.example.pc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pc.entities.Pc;
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

    @RequestMapping("/supprimerPc")
    public String supprimerPc(@RequestParam("id") Long id,
            ModelMap modelMap,
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