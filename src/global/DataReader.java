package global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import member.Member;

public class DataReader {
	public static Collection<Member> parseData(String filename) {
		ArrayList<Member> rv = new ArrayList<>();
		try {
			Reader in = new FileReader(filename);
			final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
			try {
			    for (final CSVRecord record : parser) {
			        Member mem = Member.readFromCSVRecord(record);
			        rv.add(mem);
			    }
			} finally {
			    parser.close();
			    in.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rv;
	}
}
