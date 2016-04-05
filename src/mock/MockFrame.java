package mock;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import com.iup.tp.twitup.ihm.TwitupIcon;

public class MockFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 277804074832123658L;

	/**
	 * Fenetre principale de l'application.
	 */
	protected JFrame mFrame;

	public MockFrame() {
		this.initGUI();
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		mFrame = new JFrame("Mock");
		mFrame.setLayout(new GridBagLayout());
		mFrame.setBackground(Color.lightGray);
		//mFrame.setIconImage(TwitupIcon.LOGO_IUP_ICON_50.getImage());
		mFrame.setResizable(false);
		mFrame.setSize(400, 400);
	}

	public void setCenterComponent(JComponent centerComponent) {
		JPanel componentPanel = new JPanel(new GridBagLayout());
		componentPanel.add(centerComponent, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(
						0, 0, 0, 0), 0, 0));
		
		mFrame.setContentPane(componentPanel);
	}

	public void showGui() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
						.getScreenSize();
				mFrame.setLocation((screenSize.width - mFrame.getWidth()) / 2,
						(screenSize.height - mFrame.getHeight()) / 2);

				mFrame.setVisible(true);
			}
		});
	}
}
