package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipements")
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    private String description;

    @ManyToMany(mappedBy = "equipements")
    private Set<Salle> salles = new HashSet<>();

    public Equipement() {}

    public Equipement(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public Set<Salle> getSalles() { return salles; }

    @Override
    public String toString() {
        return "Equipement{" + nom + "}";
    }
}
