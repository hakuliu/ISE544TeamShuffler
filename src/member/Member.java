package member;

import org.apache.commons.csv.CSVRecord;

public class Member {
	//I'm lazy right now so I don't really want to parse each language and nation string into enums.
	//we'll just have to deal with string compareTo's...
	String name;
	String nation;
	boolean male;
	boolean den;
	String[] languages;
	Availability avail;
	
	public Member() {
		this.avail = new Availability();
	}

	public static Member readFromCSVRecord(CSVRecord record) {
//		Member m = new Member();
		Member m = createRandomMember();
		String last = record.get("Last Name");
        String first = record.get("First name");
        m.name = new String(first + " " + last);
        
        String nation = record.get("Nation");
        m.nation = nation;
        
        String isden = record.get("On-Campus or DEN");
        m.den = isden.equalsIgnoreCase("DEN");
        
        String gender = record.get("Gender");
        m.male = gender.equalsIgnoreCase("Male");
        
		return m;
	}
	
	public static Member createRandomMember() {
		//get a list of possible options
		Member rv = new Member();
		rv.name = "test";
		rv.nation = "ttt";
		return rv;
	}
}
