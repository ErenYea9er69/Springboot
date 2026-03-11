package com.example.pc.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;

public interface PcService {
    Pc savePc(Pc p);
    Pc updatePc(Pc p);
    void deletePc(Pc p);
    void deletePcById(Long id);
    Pc getPc(Long id);
    List<Pc> getAllPcs();
    Page<Pc> getAllPcsParPage(int page, int size);
    
    List<Style> getAllStyles();
}