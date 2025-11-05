// src/jeu/Joueur.java
package jeu;

import cartes.Carte;

public class Joueur {
    private final String nom;
    private final ZoneDeJeu zone;
    private final MainJoueur main;

    public Joueur(String nom) {
        this.nom = nom;
        this.zone = new ZoneDeJeu();
        this.main = new MainJoueur();
    }

    public String getNom() { return nom; }
    public ZoneDeJeu getZone() { return zone; }
    public MainJoueur getMain() { return main; }

    @Override
    public String toString() { return nom; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joueur)) return false;
        Joueur j = (Joueur) o;
        return nom != null ? nom.equals(j.nom) : j.nom == null;
    }

    @Override
    public int hashCode() {
        return nom != null ? nom.hashCode() : 0;
    }

    public void donner(Carte c) {
        main.prendre(c);
    }

    public Carte prendreCarte(Sabot sabot) {
        if (sabot.estVide()) return null;
        Carte c = sabot.piocher();
        main.prendre(c);
        return c;
    }

    public int donnerKmParcourus() {
        return zone.donnerKmParcourus();
    }

    public void deposer(Carte c) {
        zone.deposer(c);
    }

    public boolean estDepotAutorise(Carte c) {
        return zone.estDepotAutorise(c);
    }
}
