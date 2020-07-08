public class Point {  
	
	// ATTRIBUTS
	
	public int x,y;  
	
	// CONTRUCTEURS
	
	public Point(int x, int y){ 
		this.x=x;
		this.y=y; 
	}  
	
	// ACCESSEURS
	public int getX() {
		return x;
	}
	  
	public int getY() {
		return y;
	}
	
	// MODIFICATEURS

	public void setX(int x) {
		this.x=x;
	}
	  
	public void setY(int y) {
		this.y=y;
	}
	
	// METHODES
	
	public double distanceTo(Point p) {      
		int dx = p.x-x;      
		int dy = p.y-y;      
		return Math.sqrt(dx*dx+dy*dy);  
	}

}