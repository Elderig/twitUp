package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.User;
import com.iup.twitup.controllers.TwitupUserController;

public class UserComponent extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TwitupUserController twitupUserController;

	public User user;
	public User currentUser;

	private JLabel textUserName;
	private JLabel textUserTag;
	public JButton followButton;

	public UserComponent (User user, Integer ligne, TwitupUserController controller){

		this.user = user;

		twitupUserController = controller;

		textUserName = new JLabel(user.getName());
		textUserTag = new JLabel(user.getUserTag());

		followButton = new JButton("S'abonner");
		followButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(followButton.getText().equalsIgnoreCase("S'abonner") && twitupUserController.addFollower(user.getUserTag())){
					JOptionPane.showMessageDialog(null, "Vous êtes abonné à "+user.getUserTag(), "Abonnement OK", JOptionPane.INFORMATION_MESSAGE);
				}else if(followButton.getText().equalsIgnoreCase("Se désabonner") && twitupUserController.removeFollower(user.getUserTag())){
					JOptionPane.showMessageDialog(null, "Vous n'êtes plus abonné à "+user.getUserTag(), "Désabonnement OK", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
		});

		this.add(textUserName, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(textUserTag, new GridBagConstraints(1, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(followButton, new GridBagConstraints(2, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JButton getFollowButton() {
		return followButton;
	}

	public void setFollowButton(JButton followButton) {
		this.followButton = followButton;
	}
	
	



}