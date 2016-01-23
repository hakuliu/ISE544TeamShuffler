package teams;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import member.Member;

public class TeamModel {
	String teamName;
	DefaultListModel<Member> teamMembers;
	public TeamModel(String name) {
		this.teamName = name;
		teamMembers = new DefaultListModel<>();
	}
	public ListModel<Member> getMemberListModel() {
		return teamMembers;
	}
	public void reset() {
		this.teamMembers.removeAllElements();
	}
}
