package utils;

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
		Random random = new Random();
		int randomIndice = random.nextInt(liste.size());
		for (ListIterator<T> listeIterateur = liste.listIterator();listeIterateur.hasNext();) {
			//TODO			
		}
		
	}

}
