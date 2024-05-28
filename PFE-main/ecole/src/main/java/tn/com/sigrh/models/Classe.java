package tn.com.sigrh.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="classe")
public class Classe {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String salle;
    private int studentcount;
    @Enumerated(EnumType.STRING)
    private StatutClasse statut;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;


}
