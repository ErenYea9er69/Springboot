package com.example.pc.entities;

import java.util.Date;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
public class Pc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPc;
    
    private String nomPc;
    private Double prixPc;
    private Date dateCreation;
    
    @ManyToOne
    private Style style;
}