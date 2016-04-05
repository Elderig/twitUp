package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	/**
	 * Map User/USerComponen
	 */
	protected Map<User,UserComponent> listUsers;
	
	//Compteur de ligne pour positioner nos UserComponent
	protected Integer ligne;
	
	public TwitupListUserView(IDatabase database, TwitupUserController twitupUserController){
		mDatabase = database;
		this.twitupUserController = twitupUserController;
	}
	
	public void init(){
		
		listUsers = new HashMap<User,UserComponent>();
		
		ligne = 0;
		
		this.setLayout(new GridBagLayout());
		
		JTextField recherche = new JTextField(10);
		recherche.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				twitupUserController.rechercherUser(recherche.getText());
			}
		});
		
		JButton buttonRechercher = new JButton("Rechercher");
		buttonRechercher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				twitupUserController.rechercherUser(recherche.getText());
			}
		});
		
		JLabel titleUserList = new JLabel(Globals.USER_LIST);
		
		this.add(titleUserList, new GridBagConstraints(0, 0, 1, 1, 1, 1,
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
	}
	
	public void afficherUser(Set<User> listToPaint){
		for(User u : listToPaint){
			if(listUsers.get(u)==null){
				addComponentUser(u);
			}
		}
		Map<User,UserComponent> listUsersToDelete = new HashMap<User, UserComponent>();
		for(UserComponent uc : listUsers.values()){
			if(!listToPaint.contains(uc.getUser())){
				listUsersToDelete.put(uc.getUser(), uc);
				removeComponentUser(uc);
			}
		}
		for(User u : listUsersToDelete.keySet()){
			if(listUsers.get(u)!=null){
				listUsers.remove(u);
			}
		}
		
		//for de modification des userComponent pour le boutton follow
		if(twitupUserController.getUser() != null){
			user = twitupUserController.getUser();
			for(UserComponent uc : listUsers.values()){
				if(uc.getUser().getName().equals(user.getName())){
					uc.getFollowButton().setVisible(false);
				}else if(user.getFollows().contains(uc.getUser().getUserTag())){
					uc.getFollowButton().setText("Se désabonner");
				}else{
					uc.getFollowButton().setText("S'abonner");
				}
			}
		}
	}
	

	public void addComponentUser(User user){

		UserComponent usercomponent=new UserComponent(user, ligne, twitupUserController);
		this.add(usercomponent,new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		listUsers.put(user, usercomponent);
		ligne++;
		refresh();
	}
	
	public void removeComponentUser(UserComponent userComponent){
		this.remove(userComponent);
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
}