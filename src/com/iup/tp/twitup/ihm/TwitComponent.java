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
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.twitup.controllers.TwitupTwitController;

public class TwitComponent extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textTwit;
	private JLabel dateTwit;
	private JButton followUser;
	
	public TwitupTwitController twitupTwitController;
	
	Date d;

	public TwitComponent(Twit twit, Integer ligne, TwitupTwitController twitupTwitController){
		
		this.twitupTwitController = twitupTwitController;
		
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
				twitupTwitController.addFollower(twit.getTwiter().getUserTag());
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

}
