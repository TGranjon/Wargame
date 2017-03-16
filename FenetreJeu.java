package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

public class FenetreJeu extends JFrame {
   
	private static final long serialVersionUID = 1L;
	private Carte carte;
	JPanel pan;
    JMenuBar menu;
    JPanel sousMenu;
    JMenuItem quitter;
    JMenuItem nouveau;
    private JSplitPane split;
  
    JMenu partie ;
    JMenuItem charger;
    JMenuItem sauvegarder;
    JMenu jeu;
   public  JButton  finTour;
	
	public static JLabel information;
    public static JLabel information2;
  
	/**
	 * Constructeur de la fenêtre de jeu
	 * @throws IOException
	 */
	public FenetreJeu() throws IOException {
		/* Création du titre et de l'icone */
		super ("WarGame");	
		ImageIcon icon = new ImageIcon(getClass().getResource("../Images/Icone.png"));
	    this.setIconImage(icon.getImage());		    
		carte = new Carte();		
		carte.setPreferredSize(new Dimension (IConfig.LARGEUR_CARTE * IConfig.NB_PIX_CASE, 
				 IConfig.HAUTEUR_CARTE * IConfig.NB_PIX_CASE));
		carte.setBackground(Color.BLACK);
		carte.setOpaque(true);
	//On crée deux conteneurs 
	   pan = new JPanel();
	   pan.setLayout(new BorderLayout());
	   pan.setLayout(new FlowLayout());
	   pan.setBackground(Color.GRAY);		 	      
	    //On construit enfin notre séparateur
	    split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, carte, pan);
	    split.setDividerLocation(605);
	    //On le passe ensuite au contentPane de notre objet FenetreJeu
	    //Placé au centre pour qu'il utilise tout l'espace disponible
	   this.getContentPane().add(split, BorderLayout.CENTER);
	   this.setPreferredSize(new Dimension (1020,750));
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 information = new JLabel();
			pan.add(information, BorderLayout.WEST);
			information.setPreferredSize(new Dimension (600,30));
			information.setLocation(0, 0);
			information.setBackground(Color.GRAY);
			information.setHorizontalAlignment(JLabel.CENTER);
			information.setOpaque(true);
			information.setText("Bienvenue");
			information2 = new JLabel();
			information2.setLocation(0, 20);
			information2.setBackground(Color.red);
			information2.setHorizontalAlignment(JLabel.CENTER);
			information2.setPreferredSize(new Dimension (600,10));
			pan.add(information2, BorderLayout.WEST);			
			repaint();
		/*** Création du bouton ***/
		finTour = new JButton ("Fin Tour");		
		pan.add(finTour, BorderLayout.EAST);
		
		  finTour.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					carte.tourJoueur=false;
					carte.reinitialiserJeu();
					repaint();
					carte.tourJoueur=true;
					carte.genererInconnu();
				}
			});
			 /* Création des menus principaux. */
			 menu = new JMenuBar();
			 jeu = new JMenu("Jeu");
			 partie = new JMenu("Partie");
			
			
			/* Création des options des menus. */
			 nouveau = new JMenuItem("Nouvelle partie");
			 quitter = new JMenuItem("Quitter");
			 sauvegarder = new JMenuItem("Sauvegarder");
			 charger = new JMenuItem("Charger");
			 
			 /* Initialisation des menus. */
			    jeu.add(nouveau);
			    jeu.addSeparator();
			    jeu.add(quitter);			    
			    partie.add(sauvegarder);
			    partie.addSeparator();
			    partie.add(charger);
			    /* Ajout des menus dans la barre de menus */
			    menu.add(jeu);
			    menu.add(partie);			     
			   this.setJMenuBar(menu);		  
		    /* Actions des menus */
		    
		    /* Quitter */
		    quitter.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) 
		    	{
		    		System.exit(0);
		    	}       
		    });
		    quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		    
		    /* Nouvelle partie */
		    nouveau.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) 
				{ 
		    		carte.generer();
		    		 repaint();
		      	}
	      });
		    nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
 
		    sauvegarder.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) 
				{ 
		    		if(!carte.generee)
						return;
					
				    JFileChooser fichier = new JFileChooser();
				    fichier.setDialogTitle("Sauvegarder fichier");
				    fichier.setCurrentDirectory(new File("."));
				    fichier.setFileFilter(new FileNameExtensionFilter("Sauvegarde wargame (*.ser)", "ser"));

				    int choix = fichier.showOpenDialog(carte);
				    if (choix != JFileChooser.APPROVE_OPTION)
				    	return;
		                
				    File fichier_choisi = fichier.getSelectedFile();

				    if(fichier_choisi.getPath().endsWith(".ser") == false)
				    	fichier_choisi = new File(fichier_choisi + ".ser");
		                
				    if (fichier_choisi.exists()){
				    	choix = JOptionPane.showConfirmDialog(carte, "Le fichier " + fichier_choisi + " existe déjà\nVoulez-vous vraiment l'écraser ?", "Fichier déjà existant", JOptionPane.YES_NO_OPTION);
				    	if (choix == JOptionPane.NO_OPTION)  return;
				    }
		                
				    carte.saveData(fichier_choisi.getPath());
					requestFocus();
		    		
		      	}			
	      });  
		    sauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		    charger.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) 
				{  
		    		JFileChooser fichier = new JFileChooser();
			        fichier.setDialogTitle("Ouvrir fichier");
			        fichier.setCurrentDirectory(new File("."));
			        fichier.setFileFilter(new FileNameExtensionFilter("Sauvegarde wargame (*.ser)", "ser"));

			        int choix = fichier.showOpenDialog(carte);
	                if (choix != JFileChooser.APPROVE_OPTION) {
	                    JOptionPane.showMessageDialog(carte, "Erreur : le fichier n'est pas conforme", "Erreur, fichier incorrect", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                
	               	File fichier_choisi = fichier.getSelectedFile();

	                if(fichier_choisi.getPath().endsWith(".ser") == false) {
	                    JOptionPane.showMessageDialog(carte, "Erreur : le fichier n'est pas conforme", "Erreur, fichier incorrect", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                	                
	                carte.loadData(fichier_choisi.getPath());
	                
					requestFocus();
					
		      	}			
	      });  
		    charger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
			
			
		    pack();
			 this.setVisible(true);    
		    
}

	/**
	 * Main
	 * @param args String[] : Paramètres de la fonction main
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		FenetreJeu fenetre = new FenetreJeu();
		fenetre.setVisible(true);

	}

}
