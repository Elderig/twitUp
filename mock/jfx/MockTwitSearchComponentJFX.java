package com.iup.tp.twitup.mock.jfx;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import com.iup.tp.twitup.mock.MockTwitListController;

public class MockTwitSearchComponentJFX extends GridPane {

	protected MockTwitListController controller;

	protected TextField searchTextField;

	protected Button searchButton;

	public MockTwitSearchComponentJFX() {

		searchTextField = new javafx.scene.control.TextField();
		searchButton = new javafx.scene.control.Button("Search");

		this.add(searchTextField , 0 , 0);
		GridPane.setFillWidth(searchTextField, true);
		GridPane.setHgrow(searchTextField, Priority.ALWAYS);
		this.add(searchButton, 1 ,0);

		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (controller != null) {
					controller.searchTwits(searchTextField.getText());
				}
			}
		});
	}

	public void setController(MockTwitListController controller) {
		this.controller = controller;
	}
}
