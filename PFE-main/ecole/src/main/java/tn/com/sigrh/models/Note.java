package tn.com.sigrh.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double tp;
    private double cc;
    private double examen;

    @Override
    public String toString() {
        return "Note{" +
                "tp=" + tp +
                ", cc=" + cc +
                ", examen=" + examen +
                ", moyenne=" + moyenne +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "matiereId" ,columnDefinition = "bigint")
    private Matiere matiere;
    @ManyToOne
    @JoinColumn(name = "coefficient_id")
    private Coefficient coefficient;
    @OneToOne(mappedBy = "note")
    private Moyenne moyenne;
    @ManyToOne
    @JoinColumn(name = "bulletin_id")
    private Bulletin bulletin;
    public void setCoefficient(Coefficient coefficient) {
        this.coefficient = coefficient;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}
