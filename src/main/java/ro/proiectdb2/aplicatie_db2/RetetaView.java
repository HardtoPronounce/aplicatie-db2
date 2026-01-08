package ro.proiectdb2.aplicatie_db2;

import java.time.LocalDate;

public class RetetaView {
    private Integer id_pacient;
    private Integer id_medicament;
    private String doza;
    private LocalDate data_start;
    private String pacientName;
    private String medicamentName;

    public RetetaView(Integer id_pacient, Integer id_medicament, String doza, LocalDate data_start,
                      String pacientName, String medicamentName) {
        this.id_pacient = id_pacient;
        this.id_medicament = id_medicament;
        this.doza = doza;
        this.data_start = data_start;
        this.pacientName = pacientName;
        this.medicamentName = medicamentName;
    }

    public Integer getId_pacient() { return id_pacient; }
    public Integer getId_medicament() { return id_medicament; }
    public String getDoza() { return doza; }
    public LocalDate getData_start() { return data_start; }
    public String getPacientName() { return pacientName; }
    public String getMedicamentName() { return medicamentName; }
}