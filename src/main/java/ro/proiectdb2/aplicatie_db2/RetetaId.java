package ro.proiectdb2.aplicatie_db2;

import java.io.Serializable;
import java.util.Objects;

public class RetetaId implements Serializable {
    private Integer id_pacient;
    private Integer id_medicament;

    public RetetaId() {}
    public RetetaId(Integer id_pacient, Integer id_medicament) {
        this.id_pacient = id_pacient;
        this.id_medicament = id_medicament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetetaId retetaId = (RetetaId) o;
        return Objects.equals(id_pacient, retetaId.id_pacient) && 
               Objects.equals(id_medicament, retetaId.id_medicament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pacient, id_medicament);
    }
}