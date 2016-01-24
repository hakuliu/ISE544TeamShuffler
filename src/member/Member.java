package member;

import org.apache.commons.csv.CSVRecord;

public class Member {
	//I'm lazy right now so I don't really want to parse each language and nation string into enums.
	//we'll just have to deal with string compareTo's...
	String name;
	String nation;
	boolean male;
	boolean den;
	String timezone;
	String major;
	String[] languages;
	String attitudestr;//not sure if i wanna parse it into int or not yet..
	
	Availability avail;
	
	public Member() {
		this.avail = new Availability();
	}

	public static Member readFromCSVRecord(CSVRecord record) {
		Member m = new Member();
		String last = record.get("Last Name");
        String first = record.get("First name");
        m.name = new String(first + " " + last);
        
        String nation = record.get("Nation");
        m.nation = nation;
        
        String isden = record.get("On-Campus or DEN");
        m.den = isden.equalsIgnoreCase("DEN");
        
        String gender = record.get("Gender");
        m.male = gender.equalsIgnoreCase("Male");
        
        String attitude = record.get("Attitude toward class");
        m.attitudestr = attitude;
        
        String major = record.get("Major");
        m.major = major;
        
        String timezone = record.get("Your Local Time zone");
        m.timezone = timezone;
        
        Availability av = new Availability();
        av.readAvailFromCSV(record);
        m.avail = av;
        
		return m;
	}
	
	public Availability getAvailability() {
		return this.avail;
	}

}
