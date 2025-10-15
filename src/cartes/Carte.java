package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return getClass().equals(obj);
	}

}
