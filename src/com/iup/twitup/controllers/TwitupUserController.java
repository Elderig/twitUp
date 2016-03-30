package com.iup.twitup.controllers;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitupConnexionView;
import com.iup.tp.twitup.ihm.TwitupInscriptionView;
import com.iup.tp.twitup.ihm.TwitupListUserView;
import com.iup.tp.twitup.ihm.TwitupProfilView;

public class TwitupUserController implements IDatabaseObserver{

	public Twitup controllerParent;
	
	public User user;
	
	public IDatabase mDatabase;
	
	public EntityManager mEntityManager;
	
	public TwitupConnexionView twitupConnexionView;
	public TwitupInscriptionView twitupInscriptionView;
	public TwitupProfilView twitupProfilView;
	public TwitupListUserView twitupListUserView;
	
	public TwitupUserController(IDatabase database, Twitup controllerParent, EntityManager mEntityManager){
		mDatabase = database;
		this.controllerParent = controllerParent;
		this.mEntityManager = mEntityManager;
		this.mDatabase.addObserver(this);
	}
	
	public TwitupConnexionView initConnexion(){
		twitupConnexionView = new TwitupConnexionView(mDatabase, this);
		twitupConnexionView.init();
		return twitupConnexionView;
	}
	
	public TwitupInscriptionView initInscription(){
		twitupInscriptionView = new TwitupInscriptionView(mDatabase, this);
		twitupInscriptionView.init();
		return twitupInscriptionView;
	}
	
	public TwitupProfilView initProfil(){
		twitupProfilView = new TwitupProfilView(mDatabase, this);
		twitupProfilView.init();
		return twitupProfilView;
	}
	
	public TwitupListUserView initListUser(){
		twitupListUserView = new TwitupListUserView(mDatabase, this);
		twitupListUserView.init();
		return twitupListUserView;
	}
	
	public boolean checkUser(String username, String password){
		if ( mDatabase.checkUser(username, password)){
			controllerParent.propagerCurrentUser(mDatabase.getUser(username, password));
			return true;
		}
		return false;
	}
	
	public void goTo(String destination){
		switch (destination) {
		case "Accueil" : controllerParent.goToAccueil();break;
		case "Connexion" : controllerParent.goToConnexion();;break;
		case "Inscription" : controllerParent.goToInscription();break;
		case "Profil" : controllerParent.goToProfil();break;
		default : ;
		}
	}
	
	public boolean addFollower(String tagToFollow){
		return user.addFollowing(tagToFollow);
	}
	
	public boolean removeFollower(String tagToFollow){
		return user.removeFollowing(tagToFollow);
	}
	
	public void inscription(String username, String password, String usertag, String url_image){
		// Création d'un utilisateur fictif
		User newUser = this.generateUser(username, password, usertag, url_image);

		// Génération du fichier utilisateur
		this.mEntityManager.sendUser(newUser);
		controllerParent.goToConnexion();
	}
	
	
	/**
	 * Génération d'un utilisateur fictif.
	 */
	protected User generateUser(String username, String password, String usertag, String url_image) {
		User newUser = new User(UUID.randomUUID(), usertag, password, username,
				new HashSet<String>(), url_image);
		return newUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		
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
		twitupListUserView.addComponentUser(addedUser);
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
	
	public void refreshProfil(User currentUser){
		twitupProfilView.setUser(currentUser);
		twitupProfilView.refresh();
	}
	
}