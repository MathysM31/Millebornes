// src/jeu/MainJoueur.java
package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {
    private final List<Carte> cartes = new ArrayList<>();

    public void prendre(Carte c) {
        cartes.add(c);
    }

    public void jouer(Carte c) {
        assert cartes.contains(c);
        cartes.remove(c);
    }

    @Override
    public Iterator<Carte> iterator() {
        return cartes.iterator();
    }

    @Override
    public String toString() {
        return cartes.toString();
    }
}
