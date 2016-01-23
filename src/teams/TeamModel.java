package teams;

import java.util.ArrayList;

import member.Member;

public class TeamModel {
	String teamName;
	ArrayList<Member> teamMembers;
	public TeamModel(String name) {
		this.teamName = name;
		teamMembers = new ArrayList<>();
	}
	
}
