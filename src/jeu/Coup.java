package jeu;

import java.util.Objects;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
    private final Joueur joueur;
    private final Carte carte;
    private final Joueur cible;

    public Coup(Joueur joueur, Carte carte, Joueur cible) {
        this.joueur = joueur;
        this.carte = carte;
        this.cible = cible;
    }

    public Joueur getJoueur() { return joueur; }
    public Carte getCarte() { return carte; }
    public Joueur getCible() { return cible; }

    public boolean estValide() {
        if (cible == null) return true;
        return cible.estDepotAutorise(carte) && ((carte instanceof Attaque || carte instanceof DebutLimite) ? !cible.equals(joueur) : true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coup)) return false;
        Coup c = (Coup) o;
        return Objects.equals(joueur, c.joueur) && Objects.equals(carte, c.carte) && Objects.equals(cible, c.cible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueur, carte, cible);
    }

    @Override
    public String toString() {
        if (cible == null) return "defausse la carte " + carte;
        if (cible.equals(joueur)) return "depose la carte " + carte + " dans sa zone de jeu";
        return "depose la carte " + carte + " dans la zone de jeu de " + cible;
    }
}
