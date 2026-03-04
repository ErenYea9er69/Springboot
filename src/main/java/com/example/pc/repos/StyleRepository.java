package com.example.pc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pc.entities.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
}