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
	@Override
	public Component getListCellRendererComponent(JList<? extends Member> list, Member value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel rv = new JPanel();

		if(isSelected) {
			Border selectedborder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
			//rv.setBorder(selectedborder);
		}
		
		rv.setLayout(new GridLayout(1, 3));
		
		JPanel infosection = new JPanel(new GridLayout(1, 2));
		
		infosection.add(getNationLabel(value));
		
		infosection.add(getDenLabel(value));
		
	
		
		JLabel name = getNameSexLabel(value);
		rv.add(name);
		rv.add(infosection);
		rv.add(value.avail.getUIView());
		
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
			rv.setText("D");
			toset = new Color(255, 204, 102);
		} else {
			rv.setText("O");
		}
		rv.setBackground(toset);
		rv.setOpaque(true);
		return rv;
	}
	
	private JLabel getNationLabel(Member mem) {
		JLabel rv = new JLabel();
		
		rv.setText(mem.nation);
		
		return rv;
	}
}
