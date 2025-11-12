package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
    public static final int NBCARTES = 6;

    private Sabot sabot;
    private final List<Joueur> joueurs = new ArrayList<>();
    private Iterator<Joueur> it = null;

    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] cartes = jeuDeCartes.donnerCartes();
        List<Carte> listeCartes = new ArrayList<>();
        java.util.Collections.addAll(listeCartes, cartes);
        listeCartes = GestionCartes.melanger(listeCartes);
        Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
        this.sabot = new Sabot(cartesMelangees);
    }

    public void inscrire(Joueur... js) {
        for (Joueur j : js) joueurs.add(j);
        it = null;
    }

    public void distribuerCartes() {
        int i = 0;
        while (true) {
            boolean tous = true;
            for (Joueur j : joueurs) if (j.getMain().toString().length() < 0) {}
            for (Joueur j : joueurs) {
                if (compteMain(j) < NBCARTES) {
                    j.prendreCarte(sabot);
                    tous = false;
                }
            }
            if (tous) break;
        }
    }

    private int compteMain(Joueur j) {
        int n = 0;
        for (Carte c : j.getMain()) n++;
        return n;
    }

    public String jouerTour(Joueur joueur) {
        StringBuilder sb = new StringBuilder();
        Carte pioche = joueur.prendreCarte(sabot);
        sb.append("Le joueur ").append(joueur.getNom()).append(" a pioche ").append(pioche).append("\n");
        sb.append("Il a dans sa main : ").append(joueur.getMain().toString()).append("\n");
        java.util.Set<Joueur> participants = new java.util.HashSet<>(joueurs);
        Coup coup = joueur.choisirCoup(participants);
        joueur.retirerDeLaMain(coup.getCarte());
        if (coup.getCible() == null) {
            sabot.ajouterCarte(coup.getCarte());
        } else {
            coup.getCible().deposer(coup.getCarte());
        }
        sb.append(joueur.getNom()).append(" ").append(coup.toString());
        return sb.toString();
    }

    public Joueur donnerJoueurSuivant() {
        if (joueurs.isEmpty()) return null;
        if (it == null || !it.hasNext()) it = joueurs.iterator();
        return it.next();
    }

    public String lancer() {
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean fin = false;
            for (Joueur j : joueurs) {
                if (sabot.estVide()) { fin = true; break; }
                sb.append(jouerTour(j)).append("\n");
                if (j.donnerKmParcourus() >= 1000) { fin = true; break; }
            }
            if (fin) break;
        }
        return sb.toString();
    }

    public Sabot getSabot() { return sabot; }
    public List<Joueur> getJoueurs() { return joueurs; }
}
