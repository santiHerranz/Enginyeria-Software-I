package domini;

public class Jugador {

	private String nom;

	Jugador (String nom)  throws Exception {
		if(nom.trim().length()==0)
			throw new Exception("Error - Falta el nom de jugador!");
		
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
