package ro.proiectdb2.aplicatie_db2;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "retete")
@IdClass(RetetaId.class) // Necessary because you have two [PK] columns
public class Reteta {
    @Id
    private Integer id_pacient;
    @Id
    private Integer id_medicament;
    
    private String doza;
    private LocalDate data_start;

    // Getters and Setters
    public Integer getId_pacient() { return id_pacient; }
    public void setId_pacient(Integer id) { this.id_pacient = id; }
    public Integer getId_medicament() { return id_medicament; }
    public void setId_medicament(Integer id) { this.id_medicament = id; }
    public String getDoza() { return doza; }
    public void setDoza(String doza) { this.doza = doza; }
    public LocalDate getData_start() { return data_start; }
    public void setData_start(LocalDate data) { this.data_start = data; }
}