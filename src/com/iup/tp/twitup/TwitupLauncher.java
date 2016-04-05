package com.iup.tp.twitup;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.core.Twitup;

import javafx.application.Application;
import javafx.stage.Stage;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncher extends Application
{

	/**
	 * Launcher.
	 * 
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */

	private Stage primaryStage;
    private BorderPane rootLayout;
    
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		// TODO Auto-generated method stub
		Twitup twitup = new Twitup();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("twitUp");

        initRootLayout();

        showPersonOverview();

	}
	

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            System.out.println(TwitupLauncher.class.getResource("/com/iup/tp/twitup/ihm/MainView.fxml"));
            loader.setLocation(TwitupLauncher.class.getResource("/com/iup/tp/twitup/ihm/MainView.fxml"));
            rootLayout = (BorderPane) loader.load();
            loader.getController();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            System.out.println(TwitupLauncher.class.getResource("/com/iup/tp/twitup/ihm/ListTwit.fxml"));
            loader.setLocation(TwitupLauncher.class.getResource("/com/iup/tp/twitup/ihm/ListTwit.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
