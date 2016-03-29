package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.User;

public class UserComponent extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel textUserName;
	private JLabel textUserTag;
	
	public UserComponent (User user, Integer ligne){
		
		textUserName = new JLabel(user.getName());
		textUserTag = new JLabel(user.getUserTag());
		
		this.add(textUserName, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(textUserTag, new GridBagConstraints(1, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));

	}

}