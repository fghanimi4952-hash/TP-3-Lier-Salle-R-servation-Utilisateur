package com.example;

import com.example.model.*;
import javax.persistence.*;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Utilisateur u = new Utilisateur("Dupont", "Jean", "jean.dupont@example.com");
            Salle s = new Salle("Salle A101", 30);
            s.setDescription("Salle de réunion équipée d’un projecteur");

            Reservation r = new Reservation(
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(1).plusHours(2),
                    "Réunion d’équipe"
            );

            u.addReservation(r);
            s.addEquipement(new Equipement("Projecteur", "Full HD"));
            s.addEquipement(new Equipement("Écran", "Tactile 65 pouces"));

            r.setSalle(s);
            em.persist(u);
            em.persist(s);

            em.getTransaction().commit();
            System.out.println(" Données insérées avec succès !");
        } finally {
            em.close();
            emf.close();
        }
    }
}
