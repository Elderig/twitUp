package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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

	public TwitupHomeView(IDatabase database, TwitupTwitController twitupTwitController){
		mDatabase = database;
		this.twitupTwitController = twitupTwitController;
	}

	public void init(){

			this.setLayout(new GridBagLayout());

			JLabel titleTwitList = new JLabel("Liste des twits");

			JButton buttonCreationTwit = new JButton("Créer un twit");
			buttonCreationTwit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					twitupTwitController.goTo("Creation Twit");
				}
			});
			
			this.add(buttonCreationTwit, new GridBagConstraints(0, ligne, 2, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
							5, 5, 0, 5), 0, 0));
			ligne++;
			this.add(titleTwitList, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
							5, 5, 0, 5), 0, 0));
			ligne++;

		}

	public void addComponentTwit(Twit twit){

		TwitComponent twitcomponent=new TwitComponent(twit,ligne);
		this.add(twitcomponent,new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		ligne++;
	}
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}