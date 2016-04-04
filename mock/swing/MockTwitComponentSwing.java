package com.iup.tp.twitup.mock.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;

public class MockTwitComponentSwing extends JPanel {

	private static final long serialVersionUID = 2327366138273969730L;

	public MockTwitComponentSwing(Twit twit) {

		this.setMinimumSize(new Dimension(100, 50));
		this.setMaximumSize(new Dimension(10000, 50));
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		this.setLayout(new GridBagLayout());

		JLabel tagLabel = new JLabel(twit.getTwiter().getUserTag());
		JLabel message = new JLabel(twit.getText().substring(0,
				Math.min(twit.getText().length(), 50)));

		this.add(tagLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		this.add(message, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
	}
	
	public void hideTwit() {
		this.setVisible(false);
	}

	public void showTwit() {
		this.setVisible(true);
	}

}
