package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import controleur.Controleur;


public class Frame extends JFrame
{
	private Controleur ctrl;
	private JPanel panelOption;
	private JPanel panelGraphique;

	public Frame(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Graphique");
		this.setSize(1000, 800); //1300, 1000 au pire

		this.panelOption = new PanelOption(this.ctrl);
		this.panelGraphique = new PanelGraphique(this.ctrl);


		this.add(this.panelOption,    BorderLayout.EAST);
		this.add(this.panelGraphique, BorderLayout.CENTER);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void maj()
	{
		this.panelGraphique.repaint();
	}
}