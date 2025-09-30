package cartes;

public enum Type {
	
	FEU("Feu rouge","Feu vert"),
	ESSENCE("Panne d'essence","Essence"),
	CREVAISON("Crevaison","Roue de secours"),
	ACCIDENT("Accident","Reparations");
	
	private Type(String attaque, String defense) {
		this.attaque = attaque;
		this.defense = defense;
	}
	
	private String attaque;
	private String defense;
	
	public String getAttaque() {
		return attaque;
	}
	public String getDefense() {
		return defense;
	}
	
	

}
