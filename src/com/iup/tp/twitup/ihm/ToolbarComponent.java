package com.iup.tp.twitup.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.twitup.controllers.TwitupTwitController;
import com.iup.twitup.controllers.TwitupUserController;

public class ToolbarComponent extends JPanel {

  public JTextPane pane;

  public JMenuBar menuBar;

  public JToolBar toolBar;
  
  public TwitupTwitController twitupTwitController;
  
  public TwitupUserController twitupUserController;
  
  protected IDatabase mDatabase;
  
  protected Integer charge=1;
  
	public ToolbarComponent(IDatabase database, TwitupTwitController twitupTwitController, TwitupUserController twitupUserController){
		toolBar=new JToolBar();
		mDatabase = database;
		this.twitupTwitController = twitupTwitController;
		this.twitupUserController = twitupUserController;
		init();
	}
	 
	
  public void init() {
	if(twitupUserController.controllerParent.getCurrentUser()!=null && charge!=0){
    JButton listTwit = new JButton("Accueil");
    listTwit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			twitupTwitController.goTo("Accueil");
		}
	});
    toolBar.add(listTwit);
    
    toolBar.addSeparator();
    
    JButton profil = new JButton("Profil");
    profil.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			twitupUserController.goTo("Profil");
		}
	});
    toolBar.add(profil);
    
    this.add(toolBar);
    charge=0;
	}

  }
  
}
