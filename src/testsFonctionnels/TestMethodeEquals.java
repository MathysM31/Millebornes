package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {

	public static void main(String[] args) {
		Borne carteBorne1 = new Borne(25);
		Borne carteBorne2 = new Borne(25);
		System.out.println("Deux cartes de 25km sont identiques ? "+ carteBorne1.equals(carteBorne2));
		
		Attaque carteFeuRouge1 = new Attaque(Type.FEU);
		Attaque carteFeuRouge2 = new Attaque(Type.FEU);
		System.out.println("Deux cartes de feux rouge sont identiques ? "+ carteFeuRouge1.equals(carteFeuRouge2));
		
		Parade carteFeuVert = new Parade(Type.FEU);
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? "+ carteFeuRouge1.equals(carteFeuVert));
	}
}
