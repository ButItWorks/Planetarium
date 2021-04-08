package it.planetarium.gammatrianguli.classes;

public class Posizione {
	private double x;
	private double y;

	public Posizione(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public boolean equals(Object obj) {
		Posizione p = (Posizione)obj;
		return (this.x == p.getX() && this.y == p.getY()) ? true : false;
	}

	public String visualizzaCoordinate()
	{
		return String.format("(%.1f ; %.1f)", this.x, this.y);
	}
}
