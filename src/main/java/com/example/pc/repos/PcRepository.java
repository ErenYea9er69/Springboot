package com.example.pc.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.pc.entities.Pc;
import com.example.pc.entities.Style;

public interface PcRepository extends JpaRepository<Pc, Long> {

    List<Pc> findByNomPc(String nom);

    List<Pc> findByNomPcContains(String nom);

    @Query("select p from Pc p where p.nomPc like %:nom and p.prixPc > :prix")
    List<Pc> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);

    @Query("select p from Pc p where p.style = ?1")
    List<Pc> findByStyle(Style style);

    List<Pc> findByStyleIdStyle(Long id);

    List<Pc> findByOrderByNomPcAsc();

    @Query("select p from Pc p order by p.nomPc ASC, p.prixPc DESC")
    List<Pc> trierPcsNomsPrix();
}