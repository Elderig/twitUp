package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.twitup.controllers.TwitupUserController;

public class TwitupProfilView extends JPanel implements IView{

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
	
	protected JLabel usernameLabel;
	protected JLabel usertagLabel;
	protected JLabel ProfilImage;
	
	public TwitupProfilView(IDatabase database, TwitupUserController twitupUserController){
		mDatabase = database;
		this.twitupUserController = twitupUserController;
	}
	
	public void init(){
		
		this.setLayout(new GridBagLayout());
			usernameLabel = new JLabel();
			usertagLabel = new JLabel();
			ProfilImage= new JLabel();

			
			
		this.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usertagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(ProfilImage, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void refresh() {
		if(user != null && user.getName() != null && user.getUserTag() != null){
			usernameLabel.setText(user.getName());
			usertagLabel.setText(user.getUserTag());
			ImageIcon newIcon=new ImageIcon(user.getAvatarPath());
			Image img = newIcon.getImage();
			Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			newIcon = new ImageIcon(newimg);
			ProfilImage.setIcon(newIcon);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}