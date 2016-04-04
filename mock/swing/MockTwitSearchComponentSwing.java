package com.iup.tp.twitup.mock.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.mock.MockTwitListController;

public class MockTwitSearchComponentSwing extends JPanel {

	private static final long serialVersionUID = 5761900769055683922L;

	protected MockTwitListController controller;

	protected JTextField searchTextField;

	protected JButton searchButton;

	public MockTwitSearchComponentSwing() {
		this.setLayout(new GridBagLayout());

		searchTextField = new JTextField();
		searchButton = new JButton("Search");

		this.add(searchTextField, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		
		this.add(searchButton, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
