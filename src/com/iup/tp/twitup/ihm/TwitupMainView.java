package com.iup.tp.twitup.ihm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.*;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.common.Globals;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.twitup.controllers.TwitupTwitController;

/**
 * Classe de la vue principale de l'application.
 */


public class TwitupMainView extends JFrame
{
		
	//Controler
	protected Twitup twitup;

	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;
	
	protected ToolbarComponent toolbar;
	
	/**
	 * Panel main
	 * @param twitup
	 * @param database
	 * @param entityManager
	 */
	public JPanel mainPanel;
	
	public CardLayout cardLayout = new CardLayout();

	protected Map<EViews,IView> views = new HashMap<EViews,IView>();
	
	/*
	 * Constructeur.
	 * 
	 * @param database
	 *            , Base de données de l'application.
	 */
	public TwitupMainView(Twitup twitup,IDatabase database, EntityManager entityManager) {
		this.twitup=twitup;
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		
		mainPanel = new JPanel();
		cardLayout = new CardLayout();
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void showGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		this.initGUI();
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
						.getScreenSize();
				setLocation((screenSize.width - getWidth()) / 6,
						(screenSize.height - getHeight()) / 4);

				// Affichage
				setVisible(true);

				pack();
			}
		});
	}


	/**
	 * Initialisation de l'IHM
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// Création de la fenetre principale
		this.setName(Globals.NAME_FRAME);
		
		JPanel p = new JPanel();
		this.setContentPane(p);
		ImageIcon iconIUP1 = new ImageIcon(getClass().getResource(Globals.URL_ICON_IUP_20));
		this.setIconImage(iconIUP1.getImage());
		
		Properties prop = PropertiesManager.loadProperties(Globals.URL_PROPERTIES);
		try{
			if(prop.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME).isEmpty()){
				prop.setProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME,UIManager.getSystemLookAndFeelClassName());
				PropertiesManager.writeProperties(prop, Globals.URL_PROPERTIES);
				UIManager.setLookAndFeel(prop.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME));
			}else{
				UIManager.setLookAndFeel(prop.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME));
			}
		}catch (ClassNotFoundException cnfe) {
			prop.setProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME,UIManager.getSystemLookAndFeelClassName());
			PropertiesManager.writeProperties(prop, Globals.URL_PROPERTIES);
			UIManager.setLookAndFeel(prop.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME));
        }

		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(Globals.TITLE_CHOOSER);
		fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
		
		Properties prop1 = PropertiesManager.loadProperties(Globals.URL_PROPERTIES);
		File f = new File(prop1.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY));
		if(!f.exists()){
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				prop1.setProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY,selectedFile.getAbsolutePath());
				PropertiesManager.writeProperties(prop1, Globals.URL_PROPERTIES);
				twitup.setmExchangeDirectoryPath(selectedFile.getAbsolutePath());
			}
		}

		
		// Menu Onglet Fichier
		JMenuBar menuBar=new JMenuBar();
		JMenu menuFichier = new JMenu(Globals.TITLE_MENU_FILE);
		JMenuItem menuItemChangeRep = new JMenuItem(Globals.TITLE_SUB_MENU_CHANGE_REP);
		menuItemChangeRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int result = fileChooser.showOpenDialog(TwitupMainView.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					prop1.setProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY,selectedFile.getAbsolutePath());
					PropertiesManager.writeProperties(prop1,Globals.URL_PROPERTIES);
					twitup.setmExchangeDirectoryPath(selectedFile.getAbsolutePath());
				}
			}
		});
		
		JMenuItem menuItemDeconnexion = new JMenuItem("Se déconnecter");
		menuItemDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				twitup.logout();
			}
		});
		
		JMenuItem menuItemListUser = new JMenuItem("Liste des utilisateurs");
		menuItemListUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitup.goToListUser();
			}
		});
		
		JMenuItem menuItemChangeUI = new JMenuItem(Globals.TITLE_SUB_MENU_CHANGE_UI);
		menuItemChangeUI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
			    HashMap<String,String> hm = new HashMap<String,String>();
			    for (UIManager.LookAndFeelInfo look : looks) {
			      hm.put(look.getName(), look.getClassName());
			    }
			    String DefaultValue="";
			    for (Entry<String, String> e : hm.entrySet()) {
			    	String value = e.getValue();
			        if(value.equals(prop.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME))){
			        	DefaultValue=e.getKey();
			        }
			    }
				String Input=(String) JOptionPane.showInputDialog(TwitupMainView.this, 
				        Globals.TITLE_SELECT_LF,
				        Globals.TITLE_OPTIONJPANE_LF,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, hm.keySet().toArray(), 
				        DefaultValue);
				if(Input!=null){
					prop.setProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME,hm.get(Input));
					PropertiesManager.writeProperties(prop, Globals.URL_PROPERTIES);	
					try {

						UIManager.setLookAndFeel(prop.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME));
						SwingUtilities.updateComponentTreeUI(TwitupMainView.this); 
						pack();
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		JMenuItem menuItemQuitter = new JMenuItem(Globals.TITLE_SUB_MENU_EXIT);
		ImageIcon iconExit = new ImageIcon(getClass().getResource(Globals.URL_ICON_EXIT));
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		menuFichier.add(menuItemListUser);
		menuItemQuitter.setIcon(iconExit);
		menuItemQuitter.setToolTipText(Globals.TOOLTIP_EXIT);
		menuFichier.add(menuItemChangeRep);
		menuFichier.add(menuItemChangeUI);
		menuFichier.add(menuItemDeconnexion);
		menuFichier.add(menuItemQuitter);
		menuBar.add(menuFichier);

		// Menu Onglet Aide
		JMenu menuAide = new JMenu(Globals.MENU_HELP);
		ImageIcon iconIUP = new ImageIcon(getClass().getResource(Globals.URL_ICON_IUP_50));
		JMenuItem menuItemAPropos = new JMenuItem(new AbstractAction(Globals.TITLE_SUB_MENU_A_PROPOS) {

			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(TwitupMainView.this, Globals.DESC_A_PROPOS, Globals.TITLE_SUB_MENU_A_PROPOS, JOptionPane.INFORMATION_MESSAGE, iconIUP);
			}
		});

		menuAide.add(menuItemAPropos);
		menuBar.add(menuAide);

		
		//JPanel p2 = new JPanel();
		p.setLayout(new BorderLayout());
		mainPanel.setLayout(cardLayout);
		//p2.add(mainPanel,BorderLayout.WEST);
		
		/*mainPanel.add(cardLayout, BorderLayout.EAST)
		mainPanel.setLayout(cardLayout);*/
	
		//
		// Ajout des composants à la fenêtre
		this.setJMenuBar(menuBar);
		p.add(mainPanel,BorderLayout.CENTER);
		toolbar=new ToolbarComponent(mDatabase,twitup.getTwitupTwitController(),twitup.getTwitupUserController());
		p.add(toolbar, BorderLayout.NORTH);

		setVisible(true);
	}
	
	public void add(EViews ev, IView iview){
		views.put(ev, iview);
		
	}
	
	public void show(EViews ev){
		mainPanel.add(views.get(ev).getComponent(),""+ev);
		cardLayout.show(mainPanel, ""+ev);
		this.pack();
	}
	
	public ToolbarComponent getToolbar() {
		return toolbar;
	}

	public void setToolbar(ToolbarComponent toolbar) {
		this.toolbar = toolbar;
	}

	public Twitup getTwitup() {
		return twitup;
	}

	public void setTwitup(Twitup twitup) {
		this.twitup = twitup;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

}
