package com.iup.twitup.controllers;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ToolbarComponent;
import com.iup.tp.twitup.ihm.TwitupConnexionView;
import com.iup.tp.twitup.ihm.TwitupHomeView;
import com.iup.tp.twitup.ihm.TwitupTwitCreationView;

public class TwitupTwitController implements IDatabaseObserver{
	
	public Twitup controllerParent;
	
	public IDatabase mDatabase;
	
	public User user; 
	
	public EntityManager mEntityManager;
	
	public TwitupHomeView accueilPanel;
	
	public TwitupTwitController(IDatabase database, Twitup controllerParent, EntityManager mEntityManager){
		mDatabase = database;
		this.controllerParent = controllerParent;
		this.mEntityManager=mEntityManager;
		mDatabase.addObserver(this);
	}
	
	public TwitupHomeView initAccueil(){
		accueilPanel = new TwitupHomeView(mDatabase, this);
		accueilPanel.init();
		return accueilPanel;
	}
	
	public TwitupTwitCreationView initCreationTwit(){
		TwitupTwitCreationView CreationTwitPanel = new TwitupTwitCreationView(mDatabase, this);
		CreationTwitPanel.init();
		return CreationTwitPanel;
	}
	
	public void addTwit(Twit twit){
		this.mEntityManager.sendTwit(twit);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void goTo(String destination){
		switch (destination) {
		case "Accueil" : controllerParent.goToAccueil();break;
		case "Creation Twit" : controllerParent.goToCreationTwit();break;
		case "Profil" : controllerParent.goToProfil();break;
		default : ;
		}
	}

	public Twitup getControllerParent() {
		return controllerParent;
	}

	public void setControllerParent(Twitup controllerParent) {
		this.controllerParent = controllerParent;
	}
	
	
	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		accueilPanel.addComponentTwit(addedTwit);
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}