package com.example.pc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pc.entities.Pc;

public interface PcRepository extends JpaRepository<Pc, Long> {
}