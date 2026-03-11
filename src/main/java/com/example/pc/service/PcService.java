package com.example.pc.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.example.pc.dto.PcDTO;
import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;

public interface PcService {
    PcDTO savePc(PcDTO p);
    PcDTO updatePc(PcDTO p);
    void deletePc(Pc p);
    void deletePcById(Long id);
    PcDTO getPc(Long id);
    List<PcDTO> getAllPcs();
    Page<PcDTO> getAllPcsParPage(int page, int size);
    List<Pc> findByNomPc(String nom);
    List<Pc> findByNomPcContains(String nom);
    List<Pc> findByNomPrix(String nom, Double prix);
    List<Pc> findByStyle(Style style);
    List<Pc> findByStyleIdStyle(Long id);
    List<Pc> findByOrderByNomPcAsc();
    List<Pc> trierPcsNomsPrix();
    PcDTO convertEntityToDto(Pc pc);
    Pc convertDtoToEntity(PcDTO pcDto);
}