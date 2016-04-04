package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.iup.tp.twitup.common.Globals;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.twitup.controllers.TwitupTwitController;

public class TwitupHomeView extends JPanel implements IView{

	public TwitupTwitController twitupTwitController;

	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;
	
	protected Integer ligne=1;
	
	protected Map<Twit,TwitComponent> listTwits;

	public TwitupHomeView(IDatabase database, TwitupTwitController twitupTwitController){
		mDatabase = database;
		this.twitupTwitController = twitupTwitController;
	}

	public void init(){

			this.setLayout(new GridBagLayout());
			
			listTwits = new HashMap<Twit,TwitComponent>();

			ligne = 1;
			
			JLabel titleTwitList = new JLabel("Liste des twits");

			JButton buttonCreationTwit = new JButton("Créer un twit");
			buttonCreationTwit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					twitupTwitController.goTo("Creation Twit");
				}
			});
			
			JTextField recherche = new JTextField(10);
			recherche.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					twitupTwitController.rechercher(recherche.getText());
				}
			});
			
			JButton buttonRechercher = new JButton("Rechercher");
			buttonRechercher.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					twitupTwitController.rechercher(recherche.getText());
				}
			});
			
			this.add(buttonCreationTwit, new GridBagConstraints(0, ligne, 2, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
							5, 5, 0, 5), 0, 0));
			ligne++;
			this.add(recherche, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
							5, 5, 0, 5), 0, 0));
			this.add(buttonRechercher, new GridBagConstraints(1, ligne, 1, 1, 1, 1,
					GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
							5, 5, 0, 5), 0, 0));
			ligne++;
			this.add(titleTwitList, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
							5, 5, 0, 5), 0, 0));
			ligne++;

		}

	public void afficherTwit(Set<Twit> listToPaint){
		for(Twit t : listToPaint){
			if(listTwits.get(t)==null){
				addComponentTwit(t);
			}
		}
		Map<Twit,TwitComponent> listTwitsToDelete = new HashMap<Twit, TwitComponent>();
		for(TwitComponent tc : listTwits.values()){
			if(!listToPaint.contains(tc.getTwit())){
				listTwitsToDelete.put(tc.getTwit(), tc);
				removeComponentTwit(tc);
			}
		}
		
		for(Twit t : listTwitsToDelete.keySet()){
			if(listTwits.get(t)!=null){
				listTwits.remove(t);
			}
		}
	}
	
	public void addComponentTwit(Twit twit){
		TwitComponent twitcomponent=new TwitComponent(twit,ligne,twitupTwitController );
		this.add(twitcomponent,new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		listTwits.put(twit, twitcomponent);
		ligne++;
		refresh();
		
	}
	
	public void removeComponentTwit(TwitComponent twitComponent){
		this.remove(twitComponent);
		ligne--;
		refresh();
	}
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void refresh() {
		if(this.getParent() != null){
			this.getParent().revalidate();
			this.getParent().repaint();
		}
		
	}

	public Integer getLigne() {
		return ligne;
	}

	public void setLigne(Integer ligne) {
		this.ligne = ligne;
	}

	public void notifNewTweet(String userTag){
		JOptionPane.showMessageDialog(TwitupHomeView.this, userTag + " a posté un nouveau twit !", "Nouveau Twit", JOptionPane.INFORMATION_MESSAGE,null);
	}
}