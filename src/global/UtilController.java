package global;

import memberpool.MemberPoolModel;
import teams.TeamModel;

public class UtilController {
	private MemberPoolModel poolmodel;
	private TeamModel[] teams;
	public UtilController() {
		this.poolmodel = new MemberPoolModel();
		
	}
	
	//if I actually wanted to do mvc this probably should give a read-only
	//but i'm lazy right now lol
	public MemberPoolModel getMemberPool() {
		return this.poolmodel;
	}
	
	public void populatedata() {
		//hard-coded 10 of these for now...
		this.teams = new TeamModel[10];
		for(int i = 0 ; i < this.teams.length ; i++) {
			this.teams[i] = new TeamModel("Team " + (i+1));
		}
		this.poolmodel.resetAndPopulateData();
	}
	
	public TeamModel[] getTeams() {
		return this.teams;
	}
	
	public void resetEverything() {
		for(int i = 0 ; i < this.teams.length ; i++) {
			this.teams[i].reset();
		}
		this.poolmodel.resetAndPopulateData();
	}
}
