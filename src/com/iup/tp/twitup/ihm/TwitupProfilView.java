package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.twitup.controllers.TwitupUserController;

public class TwitupProfilView extends JPanel implements IView{

	/**
	 * User courant
	 */
	public User user;

	/**
	 * Controller user
	 */
	public TwitupUserController twitupUserController;

	/**
	 * Base de données de l'application.
	 */
	protected IDatabase mDatabase;

	protected JTextField usernameLabel;
	protected JPasswordField userpasswordLabel;
	protected JLabel ProfilImage;
	protected JButton bouton_modifier;
	protected JButton bouton_enregistrer;
	protected JButton bouton_parcourir;
	protected String chemin_image;

	public TwitupProfilView(IDatabase database, TwitupUserController twitupUserController){
		mDatabase = database;
		this.twitupUserController = twitupUserController;
	}

	public void init(){

		this.setLayout(new GridBagLayout());
		usernameLabel = new JTextField(20);
		userpasswordLabel = new JPasswordField(20);
		ProfilImage= new JLabel();
		usernameLabel.setEditable(false);
		userpasswordLabel.setEditable(false);
		bouton_modifier=new JButton("Modifier");
		bouton_enregistrer=new JButton("Enregistrer");
		bouton_enregistrer.setVisible(false);
		bouton_parcourir=new JButton("Parcourir..");
		bouton_parcourir.setVisible(false);

		bouton_parcourir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				final JFileChooser fc = new JFileChooser();
				FileFilter imageFilter = new FileNameExtensionFilter(
						"Image files", ImageIO.getReaderFileSuffixes());
				fc.setFileFilter(imageFilter);
				int returnVal = fc.showDialog(bouton_parcourir,"Attach");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String chemin = fc.getSelectedFile().getAbsolutePath().toString();
					ImageIcon newIcon=new ImageIcon(chemin);
					Image img = newIcon.getImage();
					Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
					newIcon = new ImageIcon(newimg);
					ProfilImage.setIcon(newIcon);
					chemin_image=chemin;
				}
			}
		});

		bouton_enregistrer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				usernameLabel.setEditable(false);
				userpasswordLabel.setEditable(false);
				bouton_modifier.setVisible(true);
				bouton_enregistrer.setVisible(false);
				bouton_parcourir.setVisible(false);
				twitupUserController.getUser().setName(usernameLabel.getText());
				twitupUserController.getUser().setUserPassword(new String(userpasswordLabel.getPassword()));
				System.out.println(twitupUserController.getUser().getUserPassword());
				if(chemin_image!=null){
					twitupUserController.getUser().setAvatarPath(chemin_image);
				}
				twitupUserController.sendUser(twitupUserController.getUser());
			}
		});

		bouton_modifier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				usernameLabel.setEditable(true);
				userpasswordLabel.setEditable(true);
				bouton_modifier.setVisible(false);
				bouton_enregistrer.setVisible(true);
				bouton_parcourir.setVisible(true);
			}
		});

		this.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(userpasswordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(ProfilImage, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(bouton_parcourir, new GridBagConstraints(1, 2, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(bouton_modifier, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(bouton_enregistrer, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void refresh() {
		if(user != null && user.getName() != null && user.getUserTag() != null){
			usernameLabel.setText(user.getName());
			userpasswordLabel.setText(user.getUserTag());
			ImageIcon newIcon=new ImageIcon(user.getAvatarPath());
			Image img = newIcon.getImage();
			Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			newIcon = new ImageIcon(newimg);
			ProfilImage.setIcon(newIcon);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}