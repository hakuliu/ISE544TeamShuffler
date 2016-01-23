package member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.csv.CSVRecord;

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
	
	public void readAvailFromCSV(CSVRecord record) {
		for(int i = 0 ; i < listoavail.length ; i++) {
			String tag = getDayTagFromNumber(i);
			String optstr = record.get(tag);
			this.listoavail[i] = parseOption(optstr);
		}
	}
	//gives you the number of days in which there is at least one available time (YES or MAYBE)
	public int getDayScore() {
		int score = 0;
		for(int day = 0 ; day < DAYSPERWEEK ; day++) {
			boolean hasAvail = false;
			for(int div = 0 ; div < DAILYDIVISION ; div++) {
				int index = day * DAILYDIVISION + div;
				if(this.listoavail[index] != AvailabilityOption.NO) {
					hasAvail = true;
				}
			}
			if(hasAvail) {
				score++;
			}
		}
		return score;
	}
	
	private AvailabilityOption parseOption(String opt) {
		if (opt.equalsIgnoreCase("Yes")) {
			return AvailabilityOption.YES;
		} else if (opt.equalsIgnoreCase("Maybe")) {
			return AvailabilityOption.MAYBE;
		} else {
			return AvailabilityOption.NO;
		}
	}
	
	private String getDayTagFromNumber(int i) {
		String daystr = "";
		String divstr = "";
		
		int dayi = i / DAILYDIVISION;
		int divi = i % DAILYDIVISION;
		
		switch (dayi) {
		case 0:
			daystr = "Sun";
			break;
		case 1:
			daystr = "Mon";
			break;
		case 2:
			daystr = "Tue";
			break;
		case 3:
			daystr = "Wed";
			break;
		case 4:
			daystr = "Thu";
			break;
		case 5:
			daystr = "Fri";
			break;
		case 6:
			daystr = "Sat";
			break;
		default:
			daystr = "Sun";//idk, need a default.
			break;
		}
		
		switch (divi) {
		case 0:
			divstr = "Morn";
			break;
		case 1:
			divstr = "Aft";
			break;
		case 2:
			divstr = "Eve";
			break;
		default:
			break;
		}
		
		return daystr+divstr;
	}
	
	//takes a list of availabilities and mashes them together
	public static Availability mashUpAvailabilities(Availability[] list, Availability copyto) {
		for(int i = 0 ; i < copyto.listoavail.length ; i++) {
			AvailabilityOption resultingOpt = AvailabilityOption.YES;
			for(int j = 0 ; j < list.length ; j++) {
				AvailabilityOption tocheck = list[j].listoavail[i];
				if(tocheck == AvailabilityOption.NO) {
					resultingOpt = AvailabilityOption.NO;
					break;
				} else if (tocheck == AvailabilityOption.MAYBE) {
					resultingOpt = AvailabilityOption.MAYBE;
				}
			}
			copyto.listoavail[i] = resultingOpt;
		}
		
		return copyto;
	}
	
	public static enum AvailabilityOption {
		YES(new Color(0, 153, 51)),
		MAYBE(new Color(153, 204, 0)),
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
