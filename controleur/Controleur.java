package controleur;

import metier.Graphique;
import ihm.Frame;

public class Controleur
{

	private Graphique metier;
	private Frame ihm;

	public Controleur()
	{
		this.metier = new Graphique();
		this.ihm = new Frame(this);
	}

	public Graphique getMetier()
	{
		return this.metier;
	}

	public void maj()
	{
		this.ihm.maj();
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}