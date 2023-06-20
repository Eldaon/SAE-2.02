package ihm;

import controleur.Controleur;
import metier.*;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.*;

public class PanelGraphique extends JPanel
{

	
	private Controleur ctrl;

	public PanelGraphique(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		
		ArrayList<Arete> ensArete = this.ctrl.getMetier().getListArete();
		g2.setStroke(new BasicStroke( 4.0f ));
		for(int cpt=0; cpt < ensArete.size(); cpt++)
		{
			g2.setColor( ensArete.get(cpt).getColor() );
			g2.drawLine( ensArete.get(cpt).getSommet1().getPosX() * 7, ensArete.get(cpt).getSommet1().getPosY() * 7, 
			             ensArete.get(cpt).getSommet2().getPosX() * 7, ensArete.get(cpt).getSommet2().getPosY() * 7);
		}

		ArrayList<Region> ensRegion = this.ctrl.getMetier().getListRegion();
		g2.setStroke(new BasicStroke( 2.0f ));
		for(int cpt=0; cpt < ensRegion.size(); cpt++)
		{
			ArrayList<Sommet> ensSommet =  ensRegion.get(cpt).getEnsSommets();
			for (int cptSommet = 0 ; cptSommet < ensSommet.size() ; cptSommet ++ )
			{
				g2.setColor( Color.WHITE );
				g2.fillOval( ensSommet.get(cptSommet).getPosX() * 7 - 8, ensSommet.get(cptSommet).getPosY() * 7 - 8, 20, 20);
				
				g2.setColor( ensRegion.get(cpt).getColor() );
				g2.drawOval( ensSommet.get(cptSommet).getPosX() * 7 - 8, ensSommet.get(cptSommet).getPosY() * 7 - 8, 20, 20);
				g2.drawString(String.format("%02d", ensSommet.get(cptSommet).getId()), ensSommet.get(cptSommet).getPosX() * 7 - 5, ensSommet.get(cptSommet).getPosY() * 7 + 7 );
			}
			
		}

	}

	
}