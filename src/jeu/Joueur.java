package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    
    public void retirerDeLaMain(Carte carte) {
        main.jouer(carte);
    }

    public Set<Coup> coupsPossibles(Set<Joueur> participants) {
        Set<Coup> res = new HashSet<>();
        List<Carte> cartes = new ArrayList<>();
        for (Carte c : main) cartes.add(c);
        for (Joueur cible : participants) {
            for (Carte c : cartes) {
                Coup coup = new Coup(this, c, cible);
                if (coup.estValide()) res.add(coup);
            }
        }
        return res;
    }

    public Set<Coup> coupsDefausse() {
        Set<Coup> res = new HashSet<>();
        for (Carte c : main) res.add(new Coup(this, c, null));
        return res;
    }

    private Coup choisirAleatoire(Set<Coup> coups) {
        int n = coups.size();
        if (n == 0) return null;
        int i = new Random().nextInt(n);
        int k = 0;
        for (Coup c : coups) {
            if (k++ == i) return c;
        }
        return null;
    }

    public String afficherEtatJoueur() {
        return "Bottes=" + zone.getBottes() + ", Limite=" + (zone.donnerLimitationVitesse() == 50) + ", Bataille=" + (zone.getBatailles().isEmpty() ? null : zone.getBatailles().get(zone.getBatailles().size() - 1)) + ", Main=" + main.toString();
    }

    public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> valides = coupsPossibles(participants);
        if (!valides.isEmpty()) return choisirAleatoire(valides);
        return choisirAleatoire(coupsDefausse());
    }
}

