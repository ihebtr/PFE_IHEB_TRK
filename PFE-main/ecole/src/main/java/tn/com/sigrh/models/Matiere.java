package tn.com.sigrh.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="matiere")
public class Matiere {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int code;
    private int nb_ects;
    private int nb_hours;
    private double moyenne;

    @Override
    public String toString() {
        return "Matiere{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", nb_ects=" + nb_ects +
                ", nb_hours=" + nb_hours +
                ", Classes=" + Classes +
                ", bulletin=" + bulletin +
                ", coefficient=" + coefficient +
                ", notes=" + notes +
                ", moyennes=" + moyennes +
                '}';
    }

    @ManyToMany
    private List <Classe> Classes;
    @ManyToOne
    @JoinColumn(name = "bulletin_id")
    private Bulletin bulletin;
    @ManyToOne
    @JoinColumn(name = "coefficient_id")
    private Coefficient coefficient;
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    private List<Note> notes;
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    private List<Moyenne> moyennes;



}
