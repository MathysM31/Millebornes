package cartes;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.getType().getDefense();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parade parade) {
			return super.getType().equals(parade.getType());
		}
		return false;
	}
}
