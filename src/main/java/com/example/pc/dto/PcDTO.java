package com.example.pc.dto;

import java.util.Date;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.pc.entities.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PcDTO {
    
    private Long idPc;
    
    @NotBlank(message = "Le nom du PC est obligatoire")
    @Size(min = 4, max = 25, message = "la taille doit être comprise entre 4 et 25")
    private String nomPc;
    
    @NotNull(message = "Le prix du PC est obligatoire")
    @Min(value = 300, message = "doit être supérieur à ou égal à 300.0")
    private Double prixPc;
    
    @NotNull(message = "La date de création est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "doit être une date dans le présent ou le futur")
    private Date dateCreation;
    
    @NotNull(message = "Le style est obligatoire")
    private Style style;
    
    private String nomStyle;

    public Date getDateCreation() {
        return dateCreation != null ? new java.sql.Date(dateCreation.getTime()) : null;
    }
}