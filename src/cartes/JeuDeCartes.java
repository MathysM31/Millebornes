package cartes;

public class JeuDeCartes {
	
	private Configuration[] typesDeCartes = new Configuration[]{
			new Configuration(new Borne(25), 10),
	        new Configuration(new Borne(50), 10),
	        new Configuration(new Borne(75), 10),
	        new Configuration(new Borne(100), 12),
	        new Configuration(new Borne(200), 4),

	        new Configuration(new DebutLimite(), 4),
	        new Configuration(new FinLimite(), 6),

	        new Configuration(new Parade(Type.FEU), 14),
	        new Configuration(new Parade(Type.ESSENCE), 6),
	        new Configuration(new Parade(Type.CREVAISON), 6),
	        new Configuration(new Parade(Type.ACCIDENT), 6),

	        new Configuration(new Attaque(Type.FEU), 5),
	        new Configuration(new Attaque(Type.ESSENCE), 3),
	        new Configuration(new Attaque(Type.CREVAISON), 3),
	        new Configuration(new Attaque(Type.ACCIDENT), 3),

	        new Configuration(new Botte(Type.FEU), 1),
	        new Configuration(new Botte(Type.ESSENCE), 1),
	        new Configuration(new Botte(Type.CREVAISON), 1),
	        new Configuration(new Botte(Type.ACCIDENT), 1)
	};
	
	private static final class Configuration{
		
		private int nbExemplaires;
		private Carte carte;
		
		public Configuration(Carte carte, int nbExemplaires) {
			super();
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
		
		
		
	}
	
	public String affichageJeuCartes() {
		StringBuilder affichage = new StringBuilder();
		for (int i = 0; i < typesDeCartes.length; i++) {
			affichage.append(typesDeCartes[i].getNbExemplaires()).append(" ").append(typesDeCartes[i].getCarte().toString()+ "\n");
		}
		return affichage.toString();
	}
	
	public Carte[] donnerCartes() {
		int total = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			total += typesDeCartes[i].getNbExemplaires();
		}
		Carte[] paquet = new Carte[total];
		int k = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			for (int j = 0; j < typesDeCartes[i].getNbExemplaires(); j++) {
				paquet[k++] = typesDeCartes[i].getCarte();
			}
		}
		return paquet;
	}
	
	public boolean checkCount() {
		int total = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			total += typesDeCartes[i].getNbExemplaires();
		}
		return total == donnerCartes().length;
	}
	
}
