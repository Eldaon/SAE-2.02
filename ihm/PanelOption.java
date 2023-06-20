package ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;

import controleur.Controleur;

public class PanelOption extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JTextField txtSommet1;
	private JTextField txtSommet2;
	private JButton    btnValider;
	private JLabel     lblMessage;

	private int changement;
	private int tour;
	private Color couleur;

	public PanelOption (Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.changement = (int)(Math.random()*5) + 5;
		this.tour = 0;
		this.couleur = Color.RED;
		
		this.setLayout(new GridLayout(4, 1));


		this.txtSommet1 = new JTextField(15);
		this.txtSommet2 = new JTextField(15);

		this.btnValider = new JButton("Valider");
		this.lblMessage = new JLabel("");

		JPanel panelTmp;

		panelTmp = new JPanel();
		panelTmp.add(new JLabel("Sommet 1 : "));
		panelTmp.add(this.txtSommet1);
		this.add(panelTmp);

		panelTmp = new JPanel();
		panelTmp.add(new JLabel("Sommet 2 : "));
		panelTmp.add(this.txtSommet2);
		this.add(panelTmp);

		panelTmp = new JPanel();
		panelTmp.add(this.btnValider);
		this.add(panelTmp);

		panelTmp = new JPanel();
		panelTmp.add(this.lblMessage);
		this.add(panelTmp);

		btnValider.addActionListener(this);
	}


	public boolean changerCouleur()
	{
		if(this.tour++ == this.changement)
		{
			this.changement = (int)(Math.random()*5) + 5;
			this.tour = 0;
			this.couleur = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
			this.ctrl.getMetier().resetCouleurVisite();
			return true;
		}
		return false;
	}
	

	public void actionPerformed ( ActionEvent e )
	{
		if (e.getSource() == this.btnValider)
		{

			this.lblMessage.setText(this.ctrl.getMetier().colorierArete(this.txtSommet1.getText(), this.txtSommet2.getText(), this.couleur));

			if( this.lblMessage.getText().equals("L'arete a bien été colorié"))
			{
				this.changerCouleur();
			}
			
			this.ctrl.maj();
		}
	}

}


