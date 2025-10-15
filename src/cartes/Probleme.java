package cartes;

public abstract class Probleme extends Carte {
	
	private Type type;

	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			if (getClass() == obj.getClass()) {
				Probleme probleme = (Probleme) obj;
				return getType().equals(probleme.getType());
			}
			return false;
		}
	}

}
