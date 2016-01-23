package teams;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import global.UtilController;

public class AllTeamsView {
	JPanel panel;
	UtilController control;
	public AllTeamsView(UtilController controller) {
		this.control = controller;
		this.panel = new JPanel();
	}
	
	public JPanel build() {
		this.panel.removeAll();
		GridLayout gl = new GridLayout(2, 5);
		gl.setHgap(2);
		gl.setVgap(2);
		this.panel.setLayout(gl);
		
		for(int i = 0 ; i < 10 ; i++) {
			TeamView tv = new TeamView(this.control);
			this.panel.add(tv.build());
		}
		
		return this.panel;
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
}
