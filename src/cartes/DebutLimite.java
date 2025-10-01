package cartes;

public class DebutLimite extends Limite {

	public DebutLimite() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Debut " + super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof DebutLimite;
	}

}
