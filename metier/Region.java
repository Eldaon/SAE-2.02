package metier;

import java.util.ArrayList;

import java.awt.Color;

public class Region {
	private static int nbRegion = 0;
	private static Color[] tabColor = new Color[] {new Color(0, 0, 115, 255), new Color(0, 89, 0, 255), new Color(169, 0, 0, 255),
	                                               new Color(200, 125, 0, 255), new Color(200, 125, 115, 255), Color.CYAN, 
												   Color.BLACK, new Color(169, 0, 0, 255), new Color(175, 10, 255, 255)};

	private int id;
	private ArrayList<Sommet> ensSommets;

	public Region()
	{
		this.id = ++nbRegion;
		this.ensSommets = new ArrayList<Sommet>();
	}

	public int getId()
	{
		return this.id;
	}

	public ArrayList<Sommet> getEnsSommets()
	{
		return this.ensSommets;
	}

	public void addSommet(Sommet s)
	{
		this.ensSommets.add(s);
	}

	public Color getColor()
	{
		return Region.tabColor[this.id - 1];
	}
}