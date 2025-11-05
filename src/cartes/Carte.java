package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return getClass().equals(obj);
	}
	
	public interface Cartes {
	    Botte PRIORITAIRE = new Botte(Type.FEU);
	    Attaque FEU_ROUGE = new Attaque(Type.FEU);
	    Parade FEU_VERT = new Parade(Type.FEU);
	}

}
