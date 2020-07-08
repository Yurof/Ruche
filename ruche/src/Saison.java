import java.util.ArrayList;

public class Saison{
	
	// ATTRIBUTS
	
	private  int jour = 0;
	private String saison;
	// CONSTRUCTEURS
	
	public Saison(){
		saison="Printemps";
	} 
	// ACCESSEURS
	
	public int getJour(){
		return jour;
	}
	
	public String getSaison(){
		return saison;
	}
	// METHODES
	
	public void jourSuivant(ArrayList<Abeille> A){
		jour++;
		for(int i = 0; i< A.size();i++){
			A.get(i).vieillir();
		}
	}
	
	public void majSaison(){
		if(jour%365<91){
			saison="Printemps";
		}else{
			if(jour%365<91+92){
				saison="Ete";
			}else{
				if(jour%365<91+92+91){
					saison="Automne";
				}else{
					saison="Hiver";
				}
			}
		}
	}
}
