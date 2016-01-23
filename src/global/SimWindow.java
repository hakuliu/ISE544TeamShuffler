package global;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import memberpool.MemberPoolView;
import teams.AllTeamsView;

public class SimWindow {
	private JFrame window;
	private UtilController controller;
	
	private MemberPoolView poolview;
	public SimWindow(UtilController controller) {
		this.window = new JFrame();
		this.controller = controller;
	}
	public void build() {
		Dimension defaultDim = new Dimension(1000,700);
		window.setSize(defaultDim);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar topbar = buildMenuBar();
		JPanel mainview = buildMainView();

		window.setJMenuBar(topbar);
		window.add(mainview);
	}
	private void clearAllProgress() {
		controller.resetEverything();
	}
	private JMenuBar buildMenuBar() {
		JMenuBar topbar = new JMenuBar();
		JMenu filesMenu = new JMenu("File");
		
		JMenuItem generateRandom = new JMenuItem("Reset");
		generateRandom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllProgress();
			}
		});
		filesMenu.add(generateRandom);
		
		
		JMenuItem closemenu = new JMenuItem("Close");
		closemenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SimWindow.this.window.dispose();
			}
		});
		filesMenu.add(closemenu);
		
		topbar.add(filesMenu);
		return topbar;
	}
	
	private JPanel buildMainView() {
		JPanel view = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		view.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		
		AllTeamsView allteamsview = new AllTeamsView(this.controller);
		JPanel allteamspanel = allteamsview.build();
		view.add(allteamspanel, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		gbc.weighty = .4;
		gbc.weightx=.7;
		poolview = new MemberPoolView(this.controller.getMemberPool());
		JPanel poolpanel = poolview.buildView();
		view.add(poolpanel, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx++;
		gbc.weightx = .3;
		JPanel mock3 = new JPanel();
		mock3.setBackground(Color.gray);
		mock3.setForeground(Color.white);
		mock3.add(new JLabel("<html>I was going to put<br>selected team's more detailed info here...<br>but we'll see if I have time...<html>"));
		view.add(mock3, gbc);
		
		return view;
	}
	
	public void show() {
		window.setVisible(true);
	}
}
