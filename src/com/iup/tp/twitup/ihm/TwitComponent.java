package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitComponent extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textTwit;
	private JLabel dateTwit;
	private JLabel userTwit;
	
	Date d;

	public TwitComponent(Twit twit, Integer ligne){
		
		// Label Text
		textTwit=new JLabel(twit.getText());
		
		// Label Date
		d=new Date(twit.getEmissionDate());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    String s = df.format(d);
		dateTwit=new JLabel(s);
		
		// Label User
		userTwit=new JLabel(twit.getTwiter().getName());
		
		this.add(textTwit, new GridBagConstraints(0, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(dateTwit, new GridBagConstraints(1, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(userTwit, new GridBagConstraints(2, ligne, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));		
	}

}
