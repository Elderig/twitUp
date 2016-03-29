package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.common.Globals;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.twitup.controllers.TwitupUserController;

public class TwitupListUserView extends JPanel implements IView{
	
	/**
	 * User courant
	 */
	public User user;
	
	/**
	 * Controller user
	 */
	public TwitupUserController twitupUserController;
	
	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;
	
	//Compteur de ligne pour positioner nos UserComponent
	protected Integer ligne = 0;
	
	public TwitupListUserView(IDatabase database, TwitupUserController twitupUserController){
		mDatabase = database;
		this.twitupUserController = twitupUserController;
	}
	
	public void init(){
		
		this.setLayout(new GridBagLayout());
		
		JLabel titleUserList = new JLabel(Globals.USER_LIST);
		
		this.add(titleUserList, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		ligne++;
	}

	public void addComponentUser(User user){

		UserComponent usercomponent=new UserComponent(user,ligne);
		this.add(usercomponent,new GridBagConstraints(0, ligne, 1, 1, 1, 1,
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
	}
}