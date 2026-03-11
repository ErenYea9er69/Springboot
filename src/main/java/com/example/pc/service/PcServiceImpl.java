package com.example.pc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.pc.dto.PcDTO;
import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;
import com.example.pc.repos.PcRepository;

@Service
public class PcServiceImpl implements PcService {

    @Autowired
    PcRepository pcRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PcDTO savePc(PcDTO p) {
        return convertEntityToDto(pcRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public PcDTO updatePc(PcDTO p) {
        return convertEntityToDto(pcRepository.save(convertDtoToEntity(p)));
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
    public PcDTO getPc(Long id) {
        return convertEntityToDto(pcRepository.findById(id).get());
    }

    @Override
    public List<PcDTO> getAllPcs() {
        return pcRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PcDTO> getAllPcsParPage(int page, int size) {
        Page<Pc> pcs = pcRepository.findAll(PageRequest.of(page, size));
        return pcs.map(this::convertEntityToDto);
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

    @Override
    public PcDTO convertEntityToDto(Pc pc) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(pc, PcDTO.class);
    }

    @Override
    public Pc convertDtoToEntity(PcDTO pcDto) {
        Pc pc = new Pc();
        pc = modelMapper.map(pcDto, Pc.class);
        return pc;
    }
}