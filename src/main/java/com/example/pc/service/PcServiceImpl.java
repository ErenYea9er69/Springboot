package com.example.pc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;
import com.example.pc.repos.PcRepository;

@Service
public class PcServiceImpl implements PcService {

    @Autowired
    PcRepository pcRepository;

    @Override
    public Pc savePc(Pc p) {
        return pcRepository.save(p);
    }

    @Override
    public Pc updatePc(Pc p) {
        return pcRepository.save(p);
    }

    @Override
    public void deletePc(Pc p) {
        pcRepository.delete(p);
    }

    @Override
    public void deletePcById(Long id) {
        pcRepository.deleteById(id);
    }

    @Override
    public Pc getPc(Long id) {
        return pcRepository.findById(id).get();
    }

    @Override
    public List<Pc> getAllPcs() {
        return pcRepository.findAll();
    }

    @Override
    public Page<Pc> getAllPcsParPage(int page, int size) {
        return pcRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Pc> findByNomPc(String nom) {
        return pcRepository.findByNomPc(nom);
    }

    @Override
    public List<Pc> findByNomPcContains(String nom) {
        return pcRepository.findByNomPcContains(nom);
    }

    @Override
    public List<Pc> findByNomPrix(String nom, Double prix) {
        return pcRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Pc> findByStyle(Style style) {
        return pcRepository.findByStyle(style);
    }

    @Override
    public List<Pc> findByStyleIdStyle(Long id) {
        return pcRepository.findByStyleIdStyle(id);
    }

    @Override
    public List<Pc> findByOrderByNomPcAsc() {
        return pcRepository.findByOrderByNomPcAsc();
    }

    @Override
    public List<Pc> trierPcsNomsPrix() {
        return pcRepository.trierPcsNomsPrix();
    }
}