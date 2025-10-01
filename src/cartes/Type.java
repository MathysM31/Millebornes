package cartes;

public enum Type {
	
	FEU("Feu rouge","Feu vert","Vehicule prioritaire"),
	ESSENCE("Panne d'essence","Essence","Citerne d'essence"),
	CREVAISON("Crevaison","Roue de secours","Increvable"),
	ACCIDENT("Accident","Reparations","As du volant");
	
	private Type(String attaque, String defense, String botte) {
		this.attaque = attaque;
		this.defense = defense;
		this.botte = botte;
	}

	private String attaque;
	private String defense;
	private String botte;
	
	public String getAttaque() {
		return attaque;
	}
	public String getDefense() {
		return defense;
	}
	public String getBotte() {
		return botte;
	}
	

}
