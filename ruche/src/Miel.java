
public class Miel {
	
	// ATTRIBUTS
	private int qtt_pollen;
	private int qtt_miel;
	protected static int MAX_MIEL=1000;
	protected static int MAX_POLLEN=1000;
	
	// CONSTRUCTEURS
	public Miel () {
		qtt_pollen =0;
		qtt_miel=MAX_POLLEN/2;
	}
	
	// ACCESSEURS
	
	public int getQttPollen() {
		return qtt_pollen;
	}
	
	public int getQttMiel() {
		return qtt_miel;
	}
	
	// MODIFICATEURS
	public void setQttPollen(int nouveau) {
		qtt_pollen=nouveau;
	}
	
	public void setQttMiel(int nouveau) {
		qtt_miel=nouveau;
	}
	public static int getMaxMiel() {
		return MAX_MIEL;
	}
	public static int getMaxPollen() {
		return MAX_POLLEN;
	}

	// METHODES
	
	public void deposer_pollen( int depot) {
		if(qtt_pollen+depot>MAX_POLLEN) { // Si vous avez trop de pollen, vous perdez du pollen .. bien vu, excellent? très bonne observation
			System.out.println("Votre réserve de pollen est rempli... Vous avez perdu "+((qtt_pollen+depot)-MAX_POLLEN)+"pollens");
			qtt_pollen=MAX_POLLEN;
		}else{ //ajout de pollen
			qtt_pollen+=depot;
		}
	}
	
	public void transformation() { // transforme le pollen en miel avec un ratio 2:1
		if(qtt_pollen == 0 || qtt_miel == MAX_MIEL){// Si pas de miel a transformer ou réserve de miel pleine ..
			return;
		}
		if(qtt_pollen>MAX_POLLEN/10) { // transforme la moitié de la réserve de pollen
			if(qtt_miel+(qtt_pollen/2)>MAX_MIEL) {
				qtt_pollen-=2*(MAX_MIEL-qtt_miel);
				qtt_miel=MAX_MIEL;
			}else{
				qtt_miel+=(qtt_pollen/2);
				qtt_pollen=qtt_pollen/2;;
			}
		}else{ // si pas bcp de pollen, transforme tout
			if(qtt_miel+qtt_pollen/2>MAX_MIEL) {
				qtt_pollen-=2*(MAX_MIEL-qtt_miel);
				qtt_miel=MAX_MIEL;
			}else {
				qtt_miel+=qtt_pollen/2;
				qtt_pollen=0;
			}
		}
	}
	
	public boolean enlever_miel(int retrait) {
		if(qtt_miel == 0) {
			return false;
		}else {
			if(qtt_miel-retrait<0) {
				System.out.println("Pas assez de miel... Vous n'avez que "+qtt_miel+" miels restants..");
				return false;
			}else{
				setQttMiel(getQttMiel()-retrait);
				System.out.println("Il reste "+getQttMiel()+" miel(s) dans la réserve.");
				return true;
			}
		}
	}
	
}
