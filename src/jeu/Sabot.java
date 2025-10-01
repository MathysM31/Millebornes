package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	
	private Carte[] cartes;
	private int nbCartes;
	private int nbOperations = 0;
	
	
	public Sabot(Carte[] cartes) {
		if (cartes == null) throw new IllegalArgumentException("cartes null");
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (cartes == null) throw new IllegalArgumentException("cartes null");
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException("Depassement de capacite du sabot");
		}
		cartes[nbCartes++] = carte;
		nbOperations++;
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		if (!it.hasNext()) {
			throw new NoSuchElementException();
		}
		Carte premiere = it.next();
		it.remove();
		return premiere;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private final class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationsReference = nbOperations;
		
		@Override
		public boolean hasNext() {
			verifierConcurrence();
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verifierConcurrence();
			if (hasNext()) {
				Carte carteNext = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carteNext;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verifierConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nbOperations++;
			nbOperationsReference++;
		}
		
		
		private void verifierConcurrence() {
			if (nbOperations != nbOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}

}
