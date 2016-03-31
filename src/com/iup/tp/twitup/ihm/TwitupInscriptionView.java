package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
	public String chemin_image;


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
				if (usernameText.getText().length()>0 && passwordText.getText().length()>0 
						&& usertagText.getText().length()>0){
					Path pathSource = Paths.get(chemin_image);
					Path pathDestination = Paths.get("C:\\Users\\Raph\\workspace\\TPTwit\\src\\resources\\images\\"+usernameText.getText()+".jpg");
					copier(pathSource,pathDestination);
					twitupUserController.inscription(usernameText.getText(), passwordText.getText(), usertagText.getText(),"C:\\Users\\Raph\\workspace\\TPTwit\\src\\resources\\images\\"+usernameText.getText()+".jpg");
				}else{
					JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur identifiants", JOptionPane.INFORMATION_MESSAGE);
					usernameText.setText("");
					passwordText.setText("");
					usertagText.setText("");
				}
			}
		});
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/default_image.jpeg"));
		chemin_image="C:\\Users\\Raph\\workspace\\TPTwit\\src\\resources\\images\\default_image.jpeg";
		icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		JLabel imageLabel=new JLabel(icon);
	
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
					String chemin = fc.getSelectedFile().getAbsolutePath().toString();
					ImageIcon newIcon=new ImageIcon(chemin);
					Image img = newIcon.getImage();
					Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
					newIcon = new ImageIcon(newimg);
					imageLabel.setIcon(newIcon);
					chemin_image=chemin;

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
		this.add(imageLabel, new GridBagConstraints(1, 4, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
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
	
	public static boolean copier(Path source, Path destination) { 
	    try {  
	       Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING); 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	        return false; 
	    } 
	    return true; 
	}
}
