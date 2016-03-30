package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.twitup.controllers.TwitupTwitController;

public class TwitComponent extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textTwit;
	private JLabel dateTwit;
	private JButton followUser;
	private User createurTwit;
	private Twit twit;
	
	public TwitupTwitController twitupTwitController;
	
	private Date d;

	public TwitComponent(Twit twitCreated, Integer ligne, TwitupTwitController controller){
		
		twitupTwitController = controller;
		
		//Twit
		this.twit = twitCreated;
		
		//User qui a écrit le twit
		createurTwit=twit.getTwiter();
		
		// Label Text
		textTwit=new JLabel(twit.getText());
		
		// Label Date
		d=new Date(twit.getEmissionDate());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    String s = df.format(d);
		dateTwit=new JLabel(s);
		
		//Button de follow
		followUser =new JButton("Follow " + twit.getTwiter().getName());
		followUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(twitupTwitController.getUser().getName() != twit.getTwiter().getName()){
					twitupTwitController.addFollower(twit.getTwiter().getUserTag());
				}else{
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous follow vous-même", "Erreur follow", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		this.add(textTwit, new GridBagConstraints(0, ligne, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		ligne++;
		this.add(dateTwit, new GridBagConstraints(1, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(followUser, new GridBagConstraints(2, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
	}

	public JLabel getTextTwit() {
		return textTwit;
	}

	public void setTextTwit(JLabel textTwit) {
		this.textTwit = textTwit;
	}

	public JLabel getDateTwit() {
		return dateTwit;
	}

	public void setDateTwit(JLabel dateTwit) {
		this.dateTwit = dateTwit;
	}

	public JButton getFollowUser() {
		return followUser;
	}

	public void setFollowUser(JButton followUser) {
		this.followUser = followUser;
	}

	public User getCreateurTwit() {
		return createurTwit;
	}

	public void setCreateurTwit(User createurTwit) {
		this.createurTwit = createurTwit;
	}

	public Twit getTwit() {
		return twit;
	}

	public void setTwit(Twit twit) {
		this.twit = twit;
	}
	
	
	
	
	
	

}
