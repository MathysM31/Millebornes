package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {

    private Sabot sabot;

    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] cartes = jeuDeCartes.donnerCartes();

        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, cartes);

        listeCartes = GestionCartes.melanger(listeCartes);

        Carte[] cartesMelangees = listeCartes.toArray(cartes);

        this.sabot = new Sabot(cartesMelangees);
    }

    public Sabot getSabot() {
        return sabot;
    }

}
