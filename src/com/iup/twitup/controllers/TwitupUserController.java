package com.iup.twitup.controllers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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
	
	public Set<User> listUsers;
	
	public TwitupConnexionView twitupConnexionView;
	public TwitupInscriptionView twitupInscriptionView;
	public TwitupProfilView twitupProfilView;
	public TwitupListUserView twitupListUserView;
	
	public TwitupUserController(IDatabase database, Twitup controllerParent, EntityManager mEntityManager){
		mDatabase = database;
		this.controllerParent = controllerParent;
		this.mEntityManager = mEntityManager;
		this.mDatabase.addObserver(this);
		listUsers = new HashSet<User>();
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
		listUsers = mDatabase.getUsers();
		twitupListUserView.init();
		return twitupListUserView;
	}
	
	public boolean checkUser(String username, String password){
		if ( mDatabase.checkUser(username, password)){
			controllerParent.propagerCurrentUser(mDatabase.getUser(username, password));
			controllerParent.refreshListTwit();
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
		if(user.addFollowing(tagToFollow)){
			sendUser(user);
			return true;
		}
		return false;
	}
	
	public boolean removeFollower(String tagToFollow){
		if (user.removeFollowing(tagToFollow)){
			sendUser(user);
			return true;
		}
		return false;
	}
	
	public void rechercherUser(String text){
		listUsers = mDatabase.getUsers();
		Set<User> listToSendToView = new HashSet<User>();
		for(User u: listUsers){
			if(text == ""){
				listToSendToView.add(u);
			}else if(u.getName().contains(text)){
				listToSendToView.add(u);
			}
		}
		twitupListUserView.afficherUser(listToSendToView);
	}
	
	public void inscription(String username, String password, String usertag, String url_image){
		// Création d'un utilisateur fictif
		User newUser = this.generateUser(username, password, usertag, url_image);

		sendUser(newUser);
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

	public void refreshUserList(){
		System.out.println("refresh user list");
		twitupListUserView.afficherUser(listUsers);
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
		listUsers = mDatabase.getUsers();
		listUsers.add(addedUser);
		twitupListUserView.afficherUser(listUsers);
		twitupListUserView.refresh();
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		listUsers = mDatabase.getUsers();
		listUsers.remove(deletedUser);
		twitupListUserView.afficherUser(listUsers);
		twitupListUserView.refresh();		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		System.out.println("Je propage l'user current modifié");
		if(modifiedUser.getName().equals(user.getName())){
			controllerParent.propagerCurrentUser(modifiedUser);
		}
		controllerParent.refreshListTwit();
		listUsers = mDatabase.getUsers();
		twitupListUserView.afficherUser(listUsers);
		twitupListUserView.refresh();
	}

	public void sendUser(User modifiedUser) {
		this.mEntityManager.sendUser(modifiedUser);
	}
	
	public void refreshProfil(User currentUser){
		twitupProfilView.setUser(currentUser);
		twitupProfilView.refresh();
	}
	
}