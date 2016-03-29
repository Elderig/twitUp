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

public class TwitupConnexionView extends JPanel implements IView  {

	/**
	 * Controller user
	 */
	public TwitupUserController twitupUserController;
	
	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;
	
	public TwitupConnexionView(IDatabase database, TwitupUserController twitupUserController){
		mDatabase = database;
		this.twitupUserController = twitupUserController;
	}
	
	public void init(){
		
		this.setLayout(new GridBagLayout());
		
		JLabel usernameLabel = new JLabel("Nom utilisateur");
		
		JTextField usernameText = new JTextField(20);
		
		JLabel passwordLabel = new JLabel("Mot de passe");
		
		JTextField passwordText = new JTextField(20);
		
		JButton buttonConnexion = new JButton("Se connecter");
		buttonConnexion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (usernameText.getText() != null && passwordText.getText() != null
					&& twitupUserController.checkUser(usernameText.getText(), passwordText.getText())){
					twitupUserController.goTo("Accueil");
					usernameText.setText("");
					passwordText.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur identifiants", JOptionPane.INFORMATION_MESSAGE);
					usernameText.setText("");
					passwordText.setText("");
				}
			}
		});
		
		JButton buttonInscription = new JButton("S'inscrire");
		buttonInscription.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				twitupUserController.goTo("Inscription");
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
		this.add(passwordText, new GridBagConstraints(1, 1, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(buttonConnexion, new GridBagConstraints(0, 2, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(buttonInscription, new GridBagConstraints(1, 2, 3, 1, 1, 1,
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
