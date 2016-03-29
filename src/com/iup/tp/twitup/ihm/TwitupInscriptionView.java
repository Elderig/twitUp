package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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

		JLabel image = new JLabel();
		
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
					twitupUserController.inscription(usernameText.getText(), passwordText.getText(), usertagText.getText(),"");
				}else{
					JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur identifiants", JOptionPane.INFORMATION_MESSAGE);
					usernameText.setText("");
					passwordText.setText("");
					usertagText.setText("");
				}
			}
		});
		
		/*ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/default_image.jpeg"));
		JLabel imageLabel=new JLabel(icon);*/
		//File f=new File(""));
		//ImagePanel ProfileImage=new ImagePanel();
		JButton btnParcourir = new JButton("Parcourir...");
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
				FileFilter imageFilter = new FileNameExtensionFilter(
					    "Image files", ImageIO.getReaderFileSuffixes());
				fc.setFileFilter(imageFilter);
				
				int returnVal = fc.showDialog(btnParcourir,"Attach");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					//This is where a real application would open the file.
					String chemin = fc.getSelectedFile().getAbsolutePath().toString();
					//imageLabel.setIcon(new ImageIcon(chemin));
					System.out.println(chemin);
				} else {
					String chemin="";
				}
			}
		}); 

		
		this.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usernameText, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(passwordText, new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usertagLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(usertagText, new GridBagConstraints(1, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(btnParcourir, new GridBagConstraints(0, 4, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		/*this.add(imageLabel, new GridBagConstraints(1, 4, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));*/
		this.add(buttonInscription, new GridBagConstraints(1, 5, 1, 1, 1, 1,
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
