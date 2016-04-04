package com.iup.tp.twitup.mock.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.mock.ITwitListObserver;

public class MockTwitListComponentSwing extends JPanel implements ITwitListObserver {

	private static final long serialVersionUID = -3252798301810589886L;

	protected Map<Twit, MockTwitComponentSwing> twitMap = new HashMap<Twit, MockTwitComponentSwing>();

	protected GridBagLayout gridBagLayout;

	protected JPanel componentPanel;

	public MockTwitListComponentSwing() {
		gridBagLayout = new GridBagLayout();
		componentPanel = new JPanel(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(componentPanel);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		
		componentPanel.setMinimumSize(new Dimension(1000,1000));
		this.setMinimumSize(new Dimension(1000, 1000));
		this.setLayout(new GridBagLayout());
		this.add(scrollPane,  new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
	}

	@Override
	public synchronized void notifyTwitListHasChanged(List<Twit> twits) {

		List<MockTwitComponentSwing> newTwits = new ArrayList<MockTwitComponentSwing>();
		for (Twit twit : twits) {

			MockTwitComponentSwing component = twitMap.get(twit);

			// Nouveau twit
			if (component == null) {
				MockTwitComponentSwing newTwitComponent = this
						.createTwitComponent(twit);
				this.addTwitComponent(twit, newTwitComponent);
				newTwits.add(newTwitComponent);
			}
		}

		List<MockTwitComponentSwing> deletedTwits = new ArrayList<MockTwitComponentSwing>();
		List<Twit> toRemove = new ArrayList<Twit>();
		for (Twit oldTwit : twitMap.keySet()) {
			if (twits.contains(oldTwit) == false) {
				MockTwitComponentSwing oldTwitComponent = twitMap.get(oldTwit);
				if (oldTwitComponent != null) {
					deletedTwits.add(oldTwitComponent);
				}
				toRemove.add(oldTwit);
			}
		}
		for (Twit remove : toRemove) {
			twitMap.remove(remove);
		}

		for (MockTwitComponentSwing oldTwitComponent : deletedTwits) {
			oldTwitComponent.hideTwit();
		}

		this.replaceTwit(twits);

		for (MockTwitComponentSwing newTwitComponent : newTwits) {
			newTwitComponent.showTwit();
		}
	}

	private void replaceTwit(List<Twit> twits) {

		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0);
		int posY = 0;
		
		for (Twit twit : twits) {
			MockTwitComponentSwing component = twitMap.get(twit);

			if (component != null) {
				gbc.gridy = posY++;
				gridBagLayout.setConstraints(component, gbc);
			}
		}
		
		this.getParent().revalidate();
		this.getParent().repaint();
	}

	protected void addTwitComponent(Twit twit, MockTwitComponentSwing component) {
		twitMap.put(twit, component);
		componentPanel.add(component);
	}

	protected MockTwitComponentSwing createTwitComponent(Twit twit) {
		MockTwitComponentSwing mockTwitComponent = new MockTwitComponentSwing(twit);
		mockTwitComponent.setVisible(false);

		return mockTwitComponent;
	}
}
