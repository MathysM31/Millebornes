package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	public static <T> T extraire(List<T> liste) {
		Random random = new Random();
		int randomIndice = random.nextInt(liste.size());
		T element = liste.get(randomIndice);
		liste.remove(randomIndice);
		return element;
	}
	
	public static <T> T extraireIterator(List<T> liste) {
		if (liste.isEmpty()) throw new IllegalArgumentException("Liste vide");
	    ListIterator<T> it = liste.listIterator();
	    int idx = new Random().nextInt(liste.size());
	    for (int i = 0; i < idx; i++) it.next();
	    T elem = it.next();
	    it.remove();
	    return elem;
	}
	
	public static <T> List<T>  melanger(List<T> liste){
		List<T> resultat =new ArrayList<>();
		while (!liste.isEmpty()) {
			resultat.add(extraire(liste));
		}
		return resultat;
	}
	
	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2){
		if (liste1.size()!=liste2.size()) {
			System.out.println("Les listes ne font pas les memes tailles");
			return false;
		}
		for (T elemList:liste1) {
			if (Collections.frequency(liste1, elemList)!=Collections.frequency(liste2, elemList)) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste){
		List<T> resultat = new ArrayList<>();
		if (liste.isEmpty()) {
			return resultat;
		}
		for (ListIterator<T> listIterateur1 =liste.listIterator();listIterateur1.hasNext();) {
			T elementCourantListe=listIterateur1.next();
			if (resultat.contains(elementCourantListe)) {
				resultat.add(resultat.lastIndexOf(elementCourantListe), elementCourantListe);
			}else {
				resultat.add(elementCourantListe);
			}
		}
		return resultat;
	}
	

	public static <T> boolean verifierRassemblement(List<T> liste) {
	    if (liste.isEmpty()) return true;
	    ListIterator<T> it1 = liste.listIterator();
	    T courant = it1.next();

	    while (it1.hasNext()) {
	        T x = it1.next();
	        if ((courant == null && x == null) || (courant != null && courant.equals(x))) {
	            continue;
	        }
	        ListIterator<T> it2 = liste.listIterator(it1.previousIndex());
	        while (it2.hasNext()) {
	            T y = it2.next();
	            if ((courant == null && y == null) || (courant != null && courant.equals(y))) {
	                return false; 
	            }
	        }
	        courant = x;
	    }
	    return true;
	}


}
