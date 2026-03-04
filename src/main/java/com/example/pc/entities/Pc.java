package com.example.pc.entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPc;

    private String nomPc;

    private Double prixPc;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    public Pc() {
        super();
    }

    public Pc(String nomPc, Double prixPc) {
        super();
        this.nomPc = nomPc;
        this.prixPc = prixPc;
    }

    public Pc(String nomPc, Double prixPc, Date dateCreation) {
        super();
        this.nomPc = nomPc;
        this.prixPc = prixPc;
        this.dateCreation = dateCreation;
    }

    public Long getIdPc() {
        return idPc;
    }

    public void setIdPc(Long idPc) {
        this.idPc = idPc;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Double getPrixPc() {
        return prixPc;
    }

    public void setPrixPc(Double prixPc) {
        this.prixPc = prixPc;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "Pc [idPc=" + idPc + ", nomPc=" + nomPc + ", prixPc=" + prixPc
                + ", dateCreation=" + dateCreation
                + ", style=" + (style != null ? style.getNomStyle() : "null") + "]";
    }
}