package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class FenetreJeu extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carte carte;

    JMenuBar menu;
    JMenuItem quitter;
    JMenuItem nouveau;
    JMenu config;
    JMenu charger;
    JMenu sauvegarder;
    JMenu jeu;
	public static JLabel information;
	
	public FenetreJeu() throws IOException {
		/* Création du titre et de l'icone */
		super ("WarGame");
		
		carte = new Carte();
	

		
		/********* Taille de la carte non correcte !**********/
		//carte.setBackground(Color.GRAY);
		carte.setPreferredSize(new Dimension (IConfig.LARGEUR_CARTE * IConfig.NB_PIX_CASE, 
				 IConfig.HAUTEUR_CARTE * IConfig.NB_PIX_CASE));
		carte.setOpaque(true);
		  this.setPreferredSize(new Dimension (IConfig.LARGEUR_CARTE * IConfig.NB_PIX_CASE, 
					 IConfig.HAUTEUR_CARTE * IConfig.NB_PIX_CASE));
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 this.setLocation(IConfig.POSITION_X, IConfig.POSITION_Y);
		
		 setContentPane(carte);
		
			this.setLayout(new BorderLayout());
			
			information = new JLabel();
			information.setPreferredSize(new Dimension(400, 15));
			information.setOpaque(true);
			information.setHorizontalAlignment(SwingConstants.CENTER);
			 
			
			JPanel panelInfo = new JPanel();
			panelInfo.add(information);
			
			this.add(Carte.panelBtn, BorderLayout.NORTH);
			this.add(panelInfo, BorderLayout.SOUTH);
		 /* Création des menus principaux. */
		 menu = new JMenuBar();
		 jeu = new JMenu("Jeu");
		 sauvegarder = new JMenu("Sauvegarder");
		 charger = new JMenu("Restaurer");
		 config = new JMenu("Configuration");

		/* Création des options des menus. */
		 nouveau = new JMenuItem("Nouvelle partie");
		 quitter = new JMenuItem("Quitter");
		 /* Initialisation des menus. */
		    jeu.add(nouveau);
		    jeu.addSeparator();
		    jeu.add(quitter);
		    /* Ajout des menus dans la barre de menus. */
		    menu.add(jeu);
		    menu.add(sauvegarder);
		    menu.add(charger);
		    menu.add(config);
		    
		    this.setJMenuBar(menu);
		    this.setVisible(true);
		    /* Actions des menus. */
		    
		    /* Quitter. */
		    quitter.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) 
		    	{
		    		System.exit(0);
		    	}       
		    });
		    quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		    
		    /* Nouvelle partie. */
		    nouveau.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) 
				{ 
		    		
		    		carte.generer();
		    		 repaint();
		    		
		      	}
	      });
		    
		    nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		 
		    
		    pack();
			 this.setVisible(true);    
		    
}

	public static void main(String[] args) throws IOException {
		
		FenetreJeu fenetre = new FenetreJeu();
		fenetre.setVisible(true);
		//Carte carte = new Carte();
		//carte.affichage();
		//int i =carte.getMonstreList (2);
		//System.out.println("la pos d ans list "+i);
		
	}

}
