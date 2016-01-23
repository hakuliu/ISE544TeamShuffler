package memberpool;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import global.DataReader;
import member.Member;

public class MemberPoolModel {
	private DefaultListModel<Member> jlistmodel;
	public MemberPoolModel() {
		jlistmodel = new DefaultListModel<>();
	}
	
	public void populateData() {
		Collection<Member> parseddata = DataReader.parseData("StudentAvailabilityTreated.csv");
		jlistmodel.removeAllElements();
		for(Member m : parseddata) {
			jlistmodel.addElement(m);
		}
	}
	
	public ListModel getModel() {
		return jlistmodel;
	}
}
