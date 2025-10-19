package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotNull
    private Integer capacite;

    private String description;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "salle_equipement",
            joinColumns = @JoinColumn(name = "salle_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id"))
    private Set<Equipement> equipements = new HashSet<>();

    public Salle() {}

    public Salle(String nom, Integer capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    public void addEquipement(Equipement e) {
        equipements.add(e);
        e.getSalles().add(this);
    }

    public void removeEquipement(Equipement e) {
        equipements.remove(e);
        e.getSalles().remove(this);
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public Integer getCapacite() { return capacite; }
    public String getDescription() { return description; }

    public void setDescription(String d) { this.description = d; }

    @Override
    public String toString() {
        return "Salle{" + nom + ", capacité=" + capacite + "}";
    }
}
