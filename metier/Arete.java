package metier;

import java.awt.Color;

public class Arete
{
	private Sommet s1;
	private Sommet s2;
	private int    valeur;
	private Color  couleur;

	public Arete(Sommet s1, Sommet s2)
	{
		this.s1 = s1;
		this.s2 = s2;
		this.valeur = 0;
		this.couleur = Color.BLACK;
	}

	public Sommet getSommet1() {return this.s1; }
	public Sommet getSommet2() {return this.s2; }
	public int    getValeur () {return this.valeur;  }
	public Color  getColor  () {return this.couleur; }

	public void setCouleur( Color couleur )
	{
		this.couleur = couleur;
	}
}