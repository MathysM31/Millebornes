package testsFonctionnels;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.*;

public class TestGestionCartes {

    
    public static <T> boolean memeMultiensemble(List<T> a, List<T> b) {
        if (a.size() != b.size()) return false;
        Set<T> valeurs = new HashSet<>(a);
        valeurs.addAll(b);
        for (T v : valeurs) {
            if (Collections.frequency(a, v) != Collections.frequency(b, v)) return false;
        }
        return true;
    }

    public static void testMelangeJeu() {
        JeuDeCartes jeu = new JeuDeCartes();

        List<Carte> listeCarteNonMelangee = new LinkedList<>();
        for (Carte c : jeu.donnerCartes()) {
            listeCarteNonMelangee.add(c);
        }

        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
        System.out.println("Avant mélange: " + listeCartes.size() + " cartes");
        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println("Après  mélange: " + listeCartes.size() + " cartes");

        boolean okMelange = GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes);
        boolean okMulti = memeMultiensemble(listeCarteNonMelangee, listeCartes);

        System.out.println("liste mélangée sans erreur ? " + (okMelange && okMulti));
    }

    public static void testRassemblementIntegers() {
        List<Integer> l0 = List.of();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,1,2,1,3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(1,4,3,2));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(1,1,2,3,1));


        List<Integer> r0 = GestionCartes.rassembler(l0);
        List<Integer> r1 = GestionCartes.rassembler(l1);
        List<Integer> r2 = GestionCartes.rassembler(l2);
        List<Integer> r3 = GestionCartes.rassembler(l3);

        System.out.println("[] rassemblée OK ? " + GestionCartes.verifierRassemblement(r0));
        System.out.println("[1,1,2,1,3] -> " + r1 + " OK ? " + GestionCartes.verifierRassemblement(r1));
        System.out.println("[1,4,3,2]   -> " + r2 + " OK ? " + GestionCartes.verifierRassemblement(r2));
        System.out.println("[1,1,2,3,1] -> " + r3 + " OK ? " + GestionCartes.verifierRassemblement(r3));
    }

    public static void testRassemblementCartes() {
        JeuDeCartes jeu = new JeuDeCartes();
        List<Carte> original = new LinkedList<>();
        for (Carte c : jeu.donnerCartes()) original.add(c);

        List<Carte> melange = new ArrayList<>(original);
        melange = GestionCartes.melanger(melange);

        List<Carte> rassemble = GestionCartes.rassembler(melange);
        System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(rassemble));
    }

    public static void main(String[] args) {
        testMelangeJeu();
        testRassemblementIntegers();
        testRassemblementCartes();
    }
}
