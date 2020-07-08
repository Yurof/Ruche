public abstract class Abeille {
	
	// ATTRIBUTS
	
	protected int age; // En jour
	protected int esperance; // En jour
	private String nom; // Rôle de chaque Abeille
	
	protected static int MAX_POLLEN_PORTABLE=15;// En unité de pollen
	protected static int RANDOM_BUTINER = 10; // qtt de pollen butiné par une ouvrière en un jour
		
	public static final double DISPERTION = 0.25; // Pourcentage
	protected static final int ESPERANCE_REINE = 240; // L'espérance d'une reine est de 7 ans, soit 8 mois si on divise par un facteur 10.
	protected static final int ESPERANCE_BOURDON = 9; // L'espérance d'un bourdon est de 3 mois , soit 9 jours si on divise par un facteur 10.
	protected static final int ESPERANCE_OUVRIERE = 9; // L'espérance d'une ouvrière est de 2 à 6 semaines en été et 4 à 7 mois en hiver mais on va prendre la moyenne soit 3 mois, soit 9 jours si on divise par un facteur 10.
	protected static final int QUOTA_PONTE_JOUR = 25; // La reine peut pondre plus de 2500 oeufs par jour en été, on le réduit d'un facteur 100.
	protected static final int MAX_OEUFS = 2800; // La reine peut pondre 400 000 oeufs par an, soit 2 800 000 oeufs dans sa vie, on va prendre 2 800 oeufs soit divisé par un facteur 1 000. 
	
	protected static final int CONSOMMATION_ETE = 1; // Unité en nombre de miel consommé par des abeilles  en été.
	protected static final int CONSOMMATION_AUTOMNE = 3; // Unité en nombre de miel consommé par des abeilles en automne.
	protected static final int CONSOMMATION_PRINTEMPS = 2; // Unité en nombre de miel consommé par des abeilles au printemps.
	protected static final int CONSOMMATION_HIVER = 4; // Unité en nombre de miel consommé par des abeilles en hiver.
	    
	protected static final int MIEL_OEUF_REINE = 10; // Unité en nombre de miel consommé par oeuf de reine produit
	protected static final int MIEL_OEUF_FAUXBOURDON = 5; // Unité en nombre de miel consommé par oeuf de faux-bourdon produit
	protected static final int MIEL_OEUF_OUVRIERE = 2; // Unité en nombre de miel consommé par oeuf d'ouvrière produit
	
	// CONSTRUCTEURS
	
	public Abeille(int age, int esperance, String nom) {
		this.age = age;
		this.esperance = esperance;
		this.nom=nom;
	}
	
	// ACCESSEURS
	
	public String getNom() {
		return nom;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getEsperance() {
		return esperance;
	}
	
	// MODIFICATEURS
	
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public void setEsperance(int esperance) {
		this.esperance=esperance;
	}
	
	// METHODES ABSTRAITES POUR LES CLASSES FILLES
	
	public abstract void manger(Saison s, Miel m);
	public abstract void vieillir();

}