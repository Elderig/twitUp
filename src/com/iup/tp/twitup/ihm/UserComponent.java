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
	
	private JLabel textUserName;
	private JLabel textUserTag;
	private JButton followUser;
	private JButton unfollowUser;
	
	public UserComponent (User user, Integer ligne, TwitupUserController controller){
		
		twitupUserController = controller;
		
		textUserName = new JLabel(user.getName());
		textUserTag = new JLabel(user.getUserTag());
		
		followUser = new JButton("Follow "+user.getName());
		followUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(twitupUserController.getUser().getName() != user.getName()){
					if(twitupUserController.addFollower(user.getUserTag())){
						JOptionPane.showMessageDialog(null, "Vous êtes abonné à "+user.getUserTag(), "Abonnement OK", JOptionPane.INFORMATION_MESSAGE);
					}
					}else{
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous follow vous-même", "Erreur follow", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		unfollowUser = new JButton("Unfollow "+user.getName());
		unfollowUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(twitupUserController.getUser().getName() != user.getName()){
					if(twitupUserController.removeFollower(user.getUserTag())){
						JOptionPane.showMessageDialog(null, "Vous n'êtes plus abonné à "+user.getUserTag(), "Désabonnement OK", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous désabonner d'un utilisateur auquel vous n'êtes pas abonné", "Erreur désabonnement", JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous désabonner de vous-même", "Erreur désabonnement", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		if(twitupUserController.getUser() != null
				&& twitupUserController.getUser().getFollows() != null
				&& twitupUserController.getUser() != null
				&& twitupUserController.getUser().getFollows().contains(user.getUserTag())){
			followUser.setEnabled(false);
		}
		
		this.add(textUserName, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(textUserTag, new GridBagConstraints(1, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(followUser, new GridBagConstraints(2, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(unfollowUser, new GridBagConstraints(3, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));

	}

}