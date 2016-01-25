package teams;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import global.UtilController;
import member.Availability;
import member.Member;

public class TeamQuickStatView {
	private TeamModel tmodel;
	private UtilController controller;
	private JPanel view;
	private Availability totalavail;
	public TeamQuickStatView(TeamModel model, UtilController controller) {
		this.tmodel = model;
		this.controller = controller;
		this.view = new JPanel();
		this.view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				highlightAllAvailable();
			}
		});
		totalavail = new Availability();//create an empty one for now.
		tmodel.teamMembers.addListDataListener(new LocalListDataListener());
	}
	
	public JPanel build() {
		this.view.setLayout(new GridLayout(1, 3));
		this.populateAggregateAvail();
		this.view.add(new JLabel("Days Available:"));
		this.view.add(new JLabel(""+this.totalavail.getDayScore()));
		this.view.add(this.totalavail.getUIView());
		return this.view;
	}
	
	private void populateAggregateAvail() {
		//idk why listmodel has this silly data backend..
		Availability[] list = new Availability[tmodel.teamMembers.size()];
		for(int i = 0 ; i < list.length ; i++) {
			list[i] = tmodel.teamMembers.getElementAt(i).getAvailability();
		}
		Availability.mashUpAvailabilities(list, totalavail);
	}
	private void update() {
		
		view.removeAll();
		build();
		view.revalidate();
	}
	private void highlightAllAvailable() {
		ArrayList<Member> allmembers = this.controller.getAllMembers();
		for(Member check : allmembers) {
			
			int score = Availability.getDayScoreBetween(check.getAvailability(), this.totalavail);
			if(score >= Math.min(3, this.totalavail.getDayScore())) {
				check.setFavor(true);
			} else {
				check.setFavor(false);
			}
		}
		this.controller.tryInvokeRefresh();
	}
	
	//this guy's private and non-static because I wanna access some variables in this object
	private class LocalListDataListener implements ListDataListener {

		@Override
		public void intervalAdded(ListDataEvent e) {
			update();
		}

		@Override
		public void intervalRemoved(ListDataEvent e) {
			update();
		}

		@Override
		public void contentsChanged(ListDataEvent e) {
			update();
		}
	}
}
