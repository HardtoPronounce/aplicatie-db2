package ro.proiectdb2.aplicatie_db2;

import jakarta.persistence.*;

@Entity
@Table(name = "medicamente")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_medicament;
    private String denumire;
    private String producator;

    // Getters and Setters
    public Integer getId_medicament() { return id_medicament; }
    public void setId_medicament(Integer id) { this.id_medicament = id; }
    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public String getProducator() { return producator; }
    public void setProducator(String producator) { this.producator = producator; }
}