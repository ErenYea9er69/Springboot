package com.example.pc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;
import com.example.pc.repos.PcRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@SpringBootTest
class PcApplicationTests {

    @Autowired
    private PcRepository pcRepository;


    @Test
    public void testCreatePc() {
        Pc pc = new Pc("Hp laptop", 1200.50, new Date());
        Pc savedPc = pcRepository.save(pc);
        assertNotNull(savedPc.getIdPc());
        System.out.println("PC Created: " + savedPc);
    }

    @Test
    public void testFindPc() {
        Optional<Pc> p = pcRepository.findById(1L);
        if (p.isPresent()) {
            System.out.println("Found PC: " + p.get());
        } else {
            System.out.println("PC not found");
        }
    }

    @Test
    public void testUpdatePc() {
        Optional<Pc> p = pcRepository.findById(1L);
        if (p.isPresent()) {
            Pc pcToUpdate = p.get();
            pcToUpdate.setPrixPc(1000.0);
            pcRepository.save(pcToUpdate);
            System.out.println("PC Updated: " + pcToUpdate);
        }
    }

    @Test
    public void testDeletePc() {
        pcRepository.deleteById(23L);
        System.out.println("PC Deleted");
    }


    @Test
    public void testFindByNomPc() {
        List<Pc> pcs = pcRepository.findByNomPc("Hp laptop");
        for (Pc p : pcs) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByNomPcContains() {
        List<Pc> pcs = pcRepository.findByNomPcContains("Mac");
        for (Pc p : pcs) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByNomPrix() {
        List<Pc> pcs = pcRepository.findByNomPrix("book", 1500.0);
        for (Pc p : pcs) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByStyle() {
        Style style = new Style();
        style.setIdStyle(1L);
        List<Pc> pcs = pcRepository.findByStyle(style);
        for (Pc p : pcs) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByOrderByNomPcAsc() {
        List<Pc> pcs = pcRepository.findByOrderByNomPcAsc();
        for (Pc p : pcs) {
            System.out.println(p);
        }
    }

    @Test
    public void testTrierPcsNomsPrix() {
        List<Pc> pcs = pcRepository.trierPcsNomsPrix();
        for (Pc p : pcs) {
            System.out.println(p);
        }
    }
}