package com.iup.tp.twitup.mock;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.mock.jfx.MockTwitListComponentJFX;
import com.iup.tp.twitup.mock.jfx.MockTwitSearchComponentJFX;
import com.iup.tp.twitup.mock.swing.MockTwitListComponentSwing;
import com.iup.tp.twitup.mock.swing.MockTwitSearchComponentSwing;

public class MockController {

	private IDatabase database;

	public MockController(IDatabase iDatabase) {
		this.database = iDatabase;
	}

	public void startMock() {
		// Création des controllers
		MockTwitListController controllerSwing = new MockTwitListController(
				this.database);
		MockTwitListController controllerJfx= new MockTwitListController(
				this.database);

		// Init SWING
		this.initSwingComponents(controllerSwing);

		// Init Jfx
		this.initJFXComponents(controllerJfx);
	}

	protected void initSwingComponents(
			MockTwitListController mockTwitListController) {
		// Création des composants graphiques SWING
		MockTwitListComponentSwing mockTwitListComponentSwing = new MockTwitListComponentSwing();
		mockTwitListController.addObserver(mockTwitListComponentSwing);
		MockTwitSearchComponentSwing mockTwitSearchComponentSwing = new MockTwitSearchComponentSwing();
		mockTwitSearchComponentSwing.setController(mockTwitListController);

		// Ajout de la fenetre SWING
		MockFrame mockFrame = new MockFrame();
		JPanel centerPanel = new JPanel(new GridBagLayout());

		centerPanel.add(mockTwitSearchComponentSwing, new GridBagConstraints(0,
				0, 1, 1, 1, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		centerPanel.add(mockTwitListComponentSwing, new GridBagConstraints(0,
				1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
		mockFrame.setCenterComponent(centerPanel);
		mockFrame.showGui();
	}

	protected void initJFXComponents(
			MockTwitListController mockTwitListController) {
		// En premier pour forcer l'init la plateforme JavaFX
		JFXPanel jfxPanel = new JFXPanel();

		// Création des composants graphiques JavaFX
		MockTwitListComponentJFX mockTwitListComponentJfx = new MockTwitListComponentJFX();
		mockTwitListController.addObserver(mockTwitListComponentJfx);
		MockTwitSearchComponentJFX mockTwitSearchComponentJfx = new MockTwitSearchComponentJFX();
		mockTwitSearchComponentJfx.setController(mockTwitListController);

		GridPane gridPane = new GridPane();
		gridPane.setVgap(5);
		gridPane.add(mockTwitSearchComponentJfx, 0, 0);
		gridPane.add(mockTwitListComponentJfx, 0, 1);

		// Création du pont entre JavaFX et SWING
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				Scene scene = new Scene(gridPane, 350, 350);
				jfxPanel.setScene(scene);
			}
		});

		// Ajout de la fenetre Javafx
		MockFrame mockFrame = new MockFrame();
		mockFrame.setCenterComponent(jfxPanel);
		mockFrame.showGui();
	}
}
