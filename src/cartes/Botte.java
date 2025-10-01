package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return super.getType().getBotte();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Botte botte) {
			return super.getType().equals(botte.getType());
		}
		return false;
	}

}
