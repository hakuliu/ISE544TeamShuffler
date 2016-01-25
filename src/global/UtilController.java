package global;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import member.Member;
import memberpool.MemberPoolModel;
import teams.TeamModel;

public class UtilController {
	private MemberPoolModel poolmodel;
	private TeamModel[] teams;
	private SimWindow parentwindow = null;
	public UtilController() {
		this.poolmodel = new MemberPoolModel();
		
	}
	public void setWindow(SimWindow window) {
		this.parentwindow = window;
	}
	public MemberPoolModel getMemberPool() {
		return this.poolmodel;
	}
	
	public void populatedata() {
		//hard-coded 12 of these for now...
		this.teams = new TeamModel[12];
		for(int i = 0 ; i < this.teams.length ; i++) {
			this.teams[i] = new TeamModel("Team " + (i+1));
		}
		this.poolmodel.resetAndPopulateData();
	}
	
	public TeamModel[] getTeams() {
		return this.teams;
	}
	//it'd be probably a lost faster if i cached this...oh well..heh.
	public ArrayList<Member> getAllMembers() {
		ArrayList<Member>rv = new ArrayList<>();
		for(int i = 0 ; i < this.getMemberPool().getModel().getSize() ; i++) {
			rv.add(this.getMemberPool().getModel().getElementAt(i));
		}
		for(int t = 0 ; t < teams.length ; t++) {
			TeamModel tm = this.teams[t];
			for(int i = 0 ; i < tm.getMemberListModel().getSize() ; i++) {
				rv.add(tm.getMemberListModel().getElementAt(i));
			}
		}
		return rv;
	}
	
	public void resetEverything() {
		for(int i = 0 ; i < this.teams.length ; i++) {
			this.teams[i].reset();
		}
		this.poolmodel.resetAndPopulateData();
	}
	
	public void tryInvokeRefresh() {
		if(this.parentwindow != null) {
			parentwindow.invokerepaint();
		}
	}
}
