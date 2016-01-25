package member;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MemberCellRenderer implements ListCellRenderer<Member>{
	private static Color favorcolor = new Color(0, 153, 51);
	@Override
	public Component getListCellRendererComponent(JList<? extends Member> list, Member value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel rv = new JPanel();

		if(isSelected) {
			Border selectedborder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
			//rv.setBorder(selectedborder);
		}
		
		rv.setLayout(new GridLayout(1, 3));
		
		JPanel infosection = new JPanel(new GridLayout(1, 5));
		
		infosection.add(getNationLabel(value));
		
		infosection.add(getDenLabel(value));
		
		infosection.add(getMajorLabel(value));
		
		infosection.add(getTimeLabel(value));
		
		infosection.add(getAttitudeLabel(value));
	
		
		JLabel name = getNameSexLabel(value);
		rv.add(name);
		rv.add(infosection);
		rv.add(value.avail.getUIView());
		if(value.favor) {
			name.setForeground(favorcolor);
		}
		return rv;
	}
	
	private JLabel getNameSexLabel(Member mem) {
		JLabel rv = new JLabel(mem.name);
		Color malecolor = new Color(204, 238, 255);
		Color femalecolor = Color.pink;
		Color toset = femalecolor;
		if(mem.male) {
			toset = malecolor;
		}
		rv.setBackground(toset);
		rv.setOpaque(true);
		return rv;
	}
	
	private JLabel getDenLabel(Member mem) {
		JLabel rv = new JLabel();
		Color toset = new Color(255, 153, 153);
		if(mem.den) {
			rv.setText("DEN");
			toset = new Color(255, 204, 102);
		} else {
			rv.setText("OnCampus");
		}
		rv.setBackground(toset);
		rv.setOpaque(true);
		return rv;
	}
	
	private JLabel getAttitudeLabel(Member mem) {
		JLabel rv = new JLabel();
		rv.setText(mem.attitudestr);
		return rv;
	}
	
	private JLabel getTimeLabel(Member mem) {
		JLabel rv = new JLabel();
		String zone = mem.timezone;
		rv.setText(zone);
		if(zone.equalsIgnoreCase("PST")) {
			rv.setBackground(new Color(153, 204, 0));
		} else if(zone.equalsIgnoreCase("EST")) {
			rv.setBackground(new Color(153, 153, 255));
		} else {
			rv.setBackground(new Color(255, 153, 102));
		}
		rv.setOpaque(true);
		return rv;
	}
	
	private JLabel getMajorLabel(Member mem) {
		JLabel rv = new JLabel();
		String major = mem.major;
		rv.setText(major);
		if(major.equalsIgnoreCase("EM")||
				major.equalsIgnoreCase("ISE")||
				major.equalsIgnoreCase("PDE")||
				major.equalsIgnoreCase("OR")||
				major.equalsIgnoreCase("SAE")) {
			//thems ISE dept
			rv.setBackground(Color.white);
		}
		rv.setOpaque(true);
		return rv;
	}
	
	private JLabel getNationLabel(Member mem) {
		JLabel rv = new JLabel();
		
		rv.setText(mem.nation);
		
		return rv;
	}
}
