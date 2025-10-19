package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dateDebut;

    @NotNull
    private LocalDateTime dateFin;

    private String motif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salle_id")
    private Salle salle;

    public Reservation() {}

    public Reservation(LocalDateTime debut, LocalDateTime fin, String motif) {
        this.dateDebut = debut;
        this.dateFin = fin;
        this.motif = motif;
    }

    public void setUtilisateur(Utilisateur u) { this.utilisateur = u; }
    public void setSalle(Salle s) { this.salle = s; }

    @Override
    public String toString() {
        return "Reservation{" + motif + ", début=" + dateDebut + ", fin=" + dateFin + "}";
    }
}
