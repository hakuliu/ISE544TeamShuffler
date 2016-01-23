package member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Availability {
	static final int DAILYDIVISION = 3;
	static final int DAYSPERWEEK = 7;
	
	private AvailabilityOption[] listoavail;
	public Availability() {
		this.listoavail = new AvailabilityOption[DAILYDIVISION * DAYSPERWEEK];
		//test
		for(int i = 0 ; i < this.listoavail.length ; i++) {
			this.listoavail[i] = AvailabilityOption.YES;
		}
	}
	
	//yeah, i'm not really supposed to put UI stuff in model but i wanna save time LOL
	public JPanel getUIView() {
		JPanel rv = new JPanel(new GridLayout(1,listoavail.length,1,0));

		for(int i = 0 ; i < listoavail.length ; i++) {
			JLabel label = new JLabel(" ");
			label.setOpaque(true);
			label.setBackground(listoavail[i].getColorForUI());
			label.setMinimumSize(new Dimension(40,20));
			rv.add(label);
		}
		
		return rv;
	}
	
	public static enum AvailabilityOption {
		YES(new Color(0, 153, 51)),
		MAYBE(new Color(255, 255, 153)),
		NO(new Color(255, 77, 77));
		
		Color uicolor;
		private AvailabilityOption(Color uicolor) {
			this.uicolor = uicolor;
		}
		
		public Color getColorForUI() {
			return this.uicolor;
		}
	}
}
