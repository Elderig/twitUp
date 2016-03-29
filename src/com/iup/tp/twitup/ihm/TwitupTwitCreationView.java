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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.twitup.controllers.TwitupTwitController;
import com.iup.twitup.controllers.TwitupUserController;

public class TwitupTwitCreationView extends JPanel implements IView{
	/**
	 * Controller user
	 */
	public TwitupTwitController twitupTwitController;
	
	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;
	
	public TwitupTwitCreationView(IDatabase database, TwitupTwitController twitupTwitController){
		mDatabase = database;
		this.twitupTwitController = twitupTwitController;
	}
	
	public void init(){
		
		this.setLayout(new GridBagLayout());
		
		JLabel titleCreationTwit = new JLabel("Création d'un Twit");
		
		JLabel MessageBodyLabel = new JLabel("Corps du message");
		
		JTextArea MessageBodyText = new JTextArea(5, 20);
		
		JButton buttonCreationTwit = new JButton("Enregistrer twit");
		buttonCreationTwit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Twit twit=new Twit(twitupTwitController.getUser(),MessageBodyText.getText());
				twitupTwitController.addTwit(twit);
				MessageBodyText.setText("");
				twitupTwitController.goTo("Accueil");
				
			}
		});
		
		
		this.add(MessageBodyLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(MessageBodyText, new GridBagConstraints(1, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(buttonCreationTwit, new GridBagConstraints(1, 1, 2, 1, 1, 1,
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