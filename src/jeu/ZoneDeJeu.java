package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
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
    private final Set<Botte> bottes = new HashSet<>();
    
    public boolean estPrioritaire() {
        for (Botte b : bottes) if (b.getType() == Type.FEU) return true;
        return false;
    }
    
    public int donnerLimitationVitesse() {
    	if (estPrioritaire()) return 200;
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
        if (c instanceof Borne borne) { bornes.add(borne); return; }
        if (c instanceof Limite limite) { limites.add(limite); return; }
        if (c instanceof Botte botte) { bottes.add(botte); return; }
        if (c instanceof Bataille bataille) { batailles.add(bataille); }
    }

    public boolean peutAvancer() {
        if (batailles.isEmpty()) return estPrioritaire();
        Bataille top = batailles.get(batailles.size() - 1);
        if (top instanceof Parade) {
            Parade p = (Parade) top;
            if (p.getType() == Type.FEU) return true;
            return estPrioritaire();
        }
        Attaque a = (Attaque) top;
        if (a.getType() == Type.FEU) return estPrioritaire();
        for (Botte b : bottes) if (b.getType() == a.getType()) return estPrioritaire();
        return false;
    }

    public boolean estDepotFeuVertAutorise() {
        if (estPrioritaire()) return false;
        if (batailles.isEmpty()) return true;
        Bataille top = batailles.get(batailles.size() - 1);
        if (top instanceof Attaque && ((Attaque) top).getType() == Type.FEU) return true;
        if (top instanceof Parade && ((Parade) top).getType() != Type.FEU) return true;
        if (top instanceof Attaque) {
            Type t = ((Attaque) top).getType();
            for (Botte b : bottes) if (b.getType() == t) return true;
        }
        return false;
    }

    private boolean estBloque() {
        if (batailles.isEmpty()) return !estPrioritaire();
        return !peutAvancer();
    }

    public boolean estDepotBorneAutorise(Borne borne) {
        if (estBloque()) return false;
        if (borne.getKm() > donnerLimitationVitesse()) return false;
        return donnerKmParcourus() + borne.getKm() <= 1000;
    }

    public boolean estDepotLimiteAutorise(Limite limite) {
        if (estPrioritaire()) return false;
        if (limites.isEmpty()) return limite instanceof DebutLimite;
        Limite top = limites.get(limites.size() - 1);
        if (limite instanceof DebutLimite) return top instanceof FinLimite;
        if (limite instanceof FinLimite) return top instanceof DebutLimite;
        return false;
    }
    
    public boolean estDepotBatailleAutorise(Bataille bataille) {
        if (bataille instanceof Attaque) {
            Type t = ((Attaque) bataille).getType();
            for (Botte b : bottes) if (b.getType() == t) return false;
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
    	if (carte instanceof Botte) return true;
        if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
        if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
        if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
        return false;
    }

    public List<Limite> getLimites() { return limites; }
    public List<Bataille> getBatailles() { return batailles; }
    public List<Borne> getBornes() { return bornes; }
    public Set<Botte> getBottes() { return bottes; }
}
