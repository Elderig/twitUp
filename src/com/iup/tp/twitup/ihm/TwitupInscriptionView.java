package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.twitup.controllers.TwitupUserController;

public class TwitupInscriptionView extends JPanel implements IView{

	/**
	 * Controller user
	 */
	public TwitupUserController twitupUserController;
	
	
	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;
	
	public TwitupInscriptionView(IDatabase database, TwitupUserController twitupUserController){
		this.mDatabase = database;
		this.twitupUserController = twitupUserController;
	}
	
	public void init(){
		
		this.setLayout(new GridBagLayout());
		
		JLabel usernameLabel = new JLabel("Nom utilisateur");
		
		JTextField usernameText = new JTextField(20);
		
		JLabel passwordLabel = new JLabel("Mot de passe");
		
		JTextField passwordText = new JTextField(20);
		
		JLabel usertagLabel = new JLabel("Tag utilisateur");
		
		JTextField usertagText = new JTextField(20);
		
		JButton buttonInscription = new JButton("S'inscrire");
		buttonInscription.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (usernameText.getText() != null && passwordText.getText() != null 
						&& usertagText.getText() != null){
					twitupUserController.inscription(usernameText.getText(), passwordText.getText(), usertagText.getText());
				}else{
					JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur identifiants", JOptionPane.INFORMATION_MESSAGE);
					usernameText.setText("");
					passwordText.setText("");
					usertagText.setText("");
				}
			}
		});
		
		this.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usernameText, new GridBagConstraints(1, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(passwordText, new GridBagConstraints(2, 1, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usertagLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usertagText, new GridBagConstraints(1, 3, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(buttonInscription, new GridBagConstraints(1, 4, 3, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
