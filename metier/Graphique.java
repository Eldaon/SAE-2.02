package metier;

import java.util.*;
import java.io.FileInputStream;
import iut.algo.*;
import java.awt.Color;
import java.awt.geom.Line2D;

public class Graphique
{
	private ArrayList<Sommet> lstSommet;
	private ArrayList<Arete>  lstArete;
	private ArrayList<Region> lstRegion;

	private ArrayList<Sommet> lstSommetVisite;
	private ArrayList<Sommet> lstCouleurVisite;

	
	public Graphique()
	{
		this.lstSommet = new ArrayList<Sommet>();
		this.lstArete  = new ArrayList<Arete> ();
		this.lstRegion = new ArrayList<Region>();

		this.lstSommetVisite = new ArrayList<Sommet>();
		this.lstCouleurVisite = new ArrayList<Sommet>();

		this.lecteurFichier();
	}

	public void lecteurFichier()
	{
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./data/Matrice.data" ) );
			sc.nextLine();

			/////////////////////////////
			// BOUCLE NOEUDS (SOMMETS) //
			/////////////////////////////

			String ligne = sc.nextLine();

			do
			{
				Decomposeur dec = new Decomposeur(ligne);
				this.lstSommet.add(new Sommet(dec.getInt(0), dec.getInt(1), dec.getInt(2)));

				ligne = sc.nextLine();
			}while(!ligne.isEmpty());

			sc.nextLine();

			////////////////////
			// BOUCLE REGIONS //
			////////////////////
			
			ligne = sc.nextLine();

			do
			{
				Region regionTmp = new Region();

				String[] tabString = ligne.split("	");

				for (int cpt = 1; cpt < tabString.length; cpt++)
				{
					regionTmp.addSommet(lstSommet.get(Integer.parseInt(tabString[cpt]) - 1));
				}

				this.lstRegion.add(regionTmp);
				
				ligne = sc.nextLine();
			}while(!ligne.isEmpty());

			sc.nextLine();

			///////////////////
			// BOUCLE ARETES //
			///////////////////
			
			ligne = sc.nextLine();

			while( sc.hasNextLine())
			{
				Sommet   sommetTmp1 = null;
				Sommet   sommetTmp2 = null;
				String[] tabString = ligne.split("	");

				for(int cpt = 0; cpt < tabString.length; cpt++)
				{
					if(cpt % 2 == 0)
						sommetTmp1 = this.lstSommet.get(Integer.parseInt(tabString[cpt]) - 1);
					else
						sommetTmp2 = this.lstSommet.get(Integer.parseInt(tabString[cpt]) - 1);

					if(sommetTmp1 != null && sommetTmp2 != null)
						this.lstArete.add(new Arete(sommetTmp1, sommetTmp2));
				}

				ligne = sc.nextLine();
			}
			

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public boolean ajouterSommet(Sommet sommet)
	{
		if (sommet == null) { return false;}
		this.lstSommet.add(sommet);
		return true;
	}

	public boolean ajouterArete(Arete arete)
	{
		if (arete == null) { return false;}
		this.lstArete.add(arete);
		return true;
	}

	public String colorierArete(String som1, String som2, Color couleur)
	{
		Sommet sommet1;
		Sommet sommet2;

		try
		{
			sommet1 = this.getSommet(Integer.parseInt(som1));
			sommet2 = this.getSommet(Integer.parseInt(som2));
		} catch (Exception e)
		{
			return( "Veuillez entrer des sommets valides" );
		}

		Arete arete1 = this.getArete(sommet1, sommet2);

		boolean sommetVisite = false;

		if (!this.aUneArete(arete1.getSommet1(), arete1.getSommet2()))
		{
			return "Veuillez entrer des sommets qui ont une arête en commun";
		}
		
		if (arete1.getColor() != Color.BLACK) 
		{ 
			return "L'arete est déjà colorié";
		}

		for (Sommet sommet : this.lstSommetVisite)
		{
			if (sommet == sommet1 || sommet == sommet2)
			{
				sommetVisite = true;
			}
		}

		if (!sommetVisite && this.lstSommetVisite.size() != 0 ) { return "Vous partez d'un sommet non-visité"; }

		if (this.lstCouleurVisite.contains(sommet1) && this.lstCouleurVisite.contains(sommet2) ) 
		{ 
			return "Cette arete forme un cycle"; 
		}


		for (Arete arete2 : this.lstArete)
		{
			int[][] segment = {   {arete1.getSommet1().getPosX(), arete1.getSommet1().getPosY(), 
			                       arete1.getSommet2().getPosX(), arete1.getSommet2().getPosY()},
			                      {arete2.getSommet1().getPosX(), arete2.getSommet1().getPosY(), 
			                       arete2.getSommet2().getPosX(), arete2.getSommet2().getPosY()} };
			
			boolean intersect = doEdgesIntersect(segment[0], segment[1]);

			if (intersect) 
			{
				if (arete2.getColor() != Color.BLACK) 
				{ 
					return "L'arete croise une arete déjà colorié";
				}
			}
		}
		
		
		arete1.setCouleur(couleur);

		if (!this.lstSommetVisite.contains(sommet1)) 
		{
			this.lstSommetVisite.add(sommet1); 
			this.lstCouleurVisite.add(sommet1);
		}
		if (!this.lstSommetVisite.contains(sommet2)) 
		{
			this.lstSommetVisite.add(sommet2);
			this.lstCouleurVisite.add(sommet2);
		}

		return "L'arete a bien été colorié";
	}

	public boolean aUneArete(Sommet s1, Sommet s2)
	{
		for (Arete arete : this.lstArete)
		{
			if (arete.getSommet1() == s1 && arete.getSommet2() == s2 ||
			    arete.getSommet1() == s2 && arete.getSommet2() == s1) { return true;}
		}
		return false;
	}	

	public Sommet getSommet(int id)
	{
		for (Sommet sommet : this.lstSommet)
		{
			if (sommet.getId() == id) { return sommet;}
		}
		return null;
	}
	
	public Arete getArete(Sommet s1, Sommet s2)
	{
		for (Arete arete : this.lstArete)
		{
			if (arete.getSommet1() == s1 && arete.getSommet2() == s2 ||
			    arete.getSommet1() == s2 && arete.getSommet2() == s1) { return arete;}
		}
		return null;
	}

	public ArrayList<Sommet> getListSommet()
	{
		return this.lstSommet;
	}

	public ArrayList<Arete> getListArete()
	{
		return this.lstArete;
	}

	public ArrayList<Region> getListRegion()
	{
		return this.lstRegion;
	}


	public static boolean doEdgesIntersect(int[] cord1, int[] cord2)
	{
		// Création des objets Line2D pour représenter les arêtes
		Line2D line1 = new Line2D.Double(cord1[0], cord1[1], cord1[2], cord1[3]);
		Line2D line2 = new Line2D.Double(cord2[0], cord2[1], cord2[2], cord2[3]);
	
		// Vérification si les lignes se croisent
		if (line1.intersectsLine(line2)) {
			// Les arêtes se croisent
	
			// Vérification si les arêtes sont adjacentes
			if (line1.getP1().equals(line2.getP1()) || line1.getP1().equals(line2.getP2()) ||
			    line1.getP2().equals(line2.getP1()) || line1.getP2().equals(line2.getP2()))
			{
				return false; // Les arêtes sont adjacentes, elles ne se croisent pas
			}
	
			// // Vérification si les arêtes sont parallèles
			// double inclinaison1 = (line1.getY2() - line1.getY1()) / (line1.getX2() - line1.getX1());
			// double inclinaison2 = (line2.getY2() - line2.getY1()) / (line2.getX2() - line2.getX1());
	
			// if (Math.abs(inclinaison1 - inclinaison2) == 0) {
			//     return false; // Les arêtes sont parallèles, elles ne se croisent pas
			// }
	
			return true; // Les arêtes se croisent
		}
		return false; // Les arêtes ne se croisent pas
	}

	public void resetCouleurVisite()
	{
		this.lstCouleurVisite = new ArrayList<Sommet>();
	}
}