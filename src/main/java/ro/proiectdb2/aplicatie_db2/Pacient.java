package ro.proiectdb2.aplicatie_db2;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacienti")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pacient;

    private String nume;
    private String prenume;
    private LocalDate data_nasterii;

    // Getters
    public Integer getId_pacient() { return id_pacient; }
    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public LocalDate getData_nasterii() { return data_nasterii; }

    // Setters
    public void setId_pacient(Integer id_pacient) { this.id_pacient = id_pacient; }
    public void setNume(String nume) { this.nume = nume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public void setData_nasterii(LocalDate data_nasterii) { this.data_nasterii = data_nasterii; }
}