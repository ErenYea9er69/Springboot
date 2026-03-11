package com.example.pc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.pc.dto.PcDTO;
import com.example.pc.repos.StyleRepository;
import com.example.pc.service.PcService;

@Controller
public class PcControllers {

    @Autowired
    PcService pcService;

    @Autowired
    StyleRepository styleRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("pc", new PcDTO());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("styles", styleRepository.findAll());
        return "formPc";
    }

    @RequestMapping("/savePc")
    public String savePc(@Valid @ModelAttribute("pc") PcDTO pc, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("mode", "new");
            modelMap.addAttribute("styles", styleRepository.findAll());
            return "formPc";
        }
        pcService.savePc(pc);
        return "redirect:/listePcs";
    }

    @RequestMapping({"/ListePcs", "/listePcs"})
    public String listePcs(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<PcDTO> prods = pcService.getAllPcsParPage(page, size);
        modelMap.addAttribute("pcs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listePcs";
    }

    @RequestMapping("/supprimerPc")
    public String supprimerPc(@RequestParam("id") Long id,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        pcService.deletePcById(id);
        Page<PcDTO> prods = pcService.getAllPcsParPage(page, size);
        modelMap.addAttribute("pcs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listePcs";
    }

    @RequestMapping("/modifierPc")
    public String editerPc(@RequestParam("id") Long id, ModelMap modelMap) {
        PcDTO p = pcService.getPc(id);
        modelMap.addAttribute("pc", p);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("styles", styleRepository.findAll());
        return "formPc";
    }

    @RequestMapping("/updatePc")
    public String updatePc(@Valid @ModelAttribute("pc") PcDTO pc, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("mode", "edit");
            modelMap.addAttribute("styles", styleRepository.findAll());
            return "formPc";
        }
        pcService.updatePc(pc);
        return "redirect:/listePcs";
    }
}