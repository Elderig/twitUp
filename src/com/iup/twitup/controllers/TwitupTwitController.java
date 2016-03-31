package com.iup.twitup.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ToolbarComponent;
import com.iup.tp.twitup.ihm.TwitComponent;
import com.iup.tp.twitup.ihm.TwitupConnexionView;
import com.iup.tp.twitup.ihm.TwitupHomeView;
import com.iup.tp.twitup.ihm.TwitupTwitCreationView;

public class TwitupTwitController implements IDatabaseObserver{
	
	public Twitup controllerParent;
	
	public IDatabase mDatabase;
	
	public User user; 
	
	public EntityManager mEntityManager;
	
	public TwitupHomeView accueilPanel;
	
	public Set<Twit> listTwits;
	
	public TwitupTwitController(IDatabase database, Twitup controllerParent, EntityManager mEntityManager){
		mDatabase = database;
		this.controllerParent = controllerParent;
		this.mEntityManager=mEntityManager;
		mDatabase.addObserver(this);
		listTwits = new HashSet<Twit>();
	}
	
	public TwitupHomeView initAccueil(){
		accueilPanel = new TwitupHomeView(mDatabase, this);
		listTwits = mDatabase.getTwits();
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
	
	public void addFollower(String tagToFollow){
		user.addFollowing(tagToFollow);
	}
	
	public void rechercher(String text){
		listTwits = mDatabase.getTwits();
		Set<Twit> listToSendToView = new HashSet<Twit>();
		for(Twit t : listTwits){
			if(text.equals("")){
				listToSendToView.add(t);
			}else if (t.getText().contains(text)
					|| t.getTwiter().getName().contains(text)
					|| t.getTags().contains(text)
					|| t.getUserTags().contains(text)){
				listToSendToView.add(t);
			}else if(text.substring(0,1).equals(Constants.USER_TAG_DELIMITER)
					&& text.substring(1, text.length()).equals(t.getTwiter().getName())){
				listToSendToView.add(t);
			}else if (text.substring(0,1).equals(Constants.WORD_TAG_DELIMITER) 
					&& t.getUserTags().contains(text.substring(1, text.length()))){
				listToSendToView.add(t);
			}	
		}
		accueilPanel.afficherTwit(listToSendToView);
	}
	
	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		listTwits = mDatabase.getTwits();
		listTwits.add(addedTwit);
		accueilPanel.afficherTwit(listTwits);
		accueilPanel.refresh();
		if(user != null && user.isFollowing(addedTwit.getTwiter()) && user.getUuid() != addedTwit.getTwiter().getUuid()){
			accueilPanel.notifNewTweet(addedTwit.getTwiter().getUserTag());
		}
		

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