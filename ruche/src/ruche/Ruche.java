package ruche;

public class Ruche {
	private int nbr_ouvrier,nbr_male;
	
	public Ruche(int nbr_ouvrier,int nbr_male){
		this.nbr_ouvrier=nbr_ouvrier;
		this.nbr_male=nbr_male;
	}
	
	
	public int getOuvrier(){
		return nbr_ouvrier;
	}
	public int getMale(){
		return nbr_male;
	}
	public String toString(){
		return ""+"nbr ouvrier: "+nbr_ouvrier+"\nnbr male: "+nbr_male;
	}
}
