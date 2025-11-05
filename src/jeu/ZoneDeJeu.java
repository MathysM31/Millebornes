// src/jeu/ZoneDeJeu.java
package jeu;

import java.util.ArrayList;
import java.util.List;
import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
    private final List<Limite> limites = new ArrayList<>();
    private final List<Bataille> batailles = new ArrayList<>();
    private final List<Borne> bornes = new ArrayList<>();

    public int donnerLimitationVitesse() {
        if (limites.isEmpty()) return 200;
        Limite top = limites.get(limites.size() - 1);
        if (top instanceof FinLimite) return 200;
        return 50;
    }

    public int donnerKmParcourus() {
        int s = 0;
        for (Borne b : bornes) s += b.getKm();
        return s;
    }

    public void deposer(Carte c) {
        if (c instanceof Borne) { bornes.add((Borne) c); return; }
        if (c instanceof Limite) { limites.add((Limite) c); return; }
        if (c instanceof Bataille) { batailles.add((Bataille) c); }
    }

    public boolean peutAvancer() {
        if (batailles.isEmpty()) return false;
        Bataille top = batailles.get(batailles.size() - 1);
        return (top instanceof Parade) && ((Parade) top).getType() == Type.FEU;
    }

    public boolean estDepotFeuVertAutorise() {
        if (batailles.isEmpty()) return true;
        Bataille top = batailles.get(batailles.size() - 1);
        if (top instanceof Attaque && ((Attaque) top).getType() == Type.FEU) return true;
        if (top instanceof Parade && ((Parade) top).getType() != Type.FEU) return true;
        return false;
    }

    private boolean estBloque() {
        if (batailles.isEmpty()) return true;
        return !peutAvancer();
    }

    public boolean estDepotBorneAutorise(Borne borne) {
        if (estBloque()) return false;
        if (borne.getKm() > donnerLimitationVitesse()) return false;
        return donnerKmParcourus() + borne.getKm() <= 1000;
    }

    public boolean estDepotLimiteAutorise(Limite limite) {
        if (limites.isEmpty()) return limite instanceof DebutLimite;
        Limite top = limites.get(limites.size() - 1);
        if (limite instanceof DebutLimite) return top instanceof FinLimite;
        if (limite instanceof FinLimite) return top instanceof DebutLimite;
        return false;
    }

    public boolean estDepotBatailleAutorise(Bataille bataille) {
        if (bataille instanceof Attaque) {
            return !estBloque();
        } else {
            Parade p = (Parade) bataille;
            if (p.getType() == Type.FEU) return estDepotFeuVertAutorise();
            if (batailles.isEmpty()) return false;
            Bataille top = batailles.get(batailles.size() - 1);
            return (top instanceof Attaque) && ((Attaque) top).getType() == p.getType();
        }
    }

    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne) return estDepotBorneAutorise((Borne) carte);
        if (carte instanceof Limite) return estDepotLimiteAutorise((Limite) carte);
        if (carte instanceof Bataille) return estDepotBatailleAutorise((Bataille) carte);
        return false;
    }

    public List<Limite> getLimites() { return limites; }
    public List<Bataille> getBatailles() { return batailles; }
    public List<Borne> getBornes() { return bornes; }
}
