package cartes;

public class Attaque extends Bataille {

	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.getType().getAttaque();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Attaque attaque) {
			return super.getType().equals(attaque.getType());
		}
		return false;
	}
	


}
