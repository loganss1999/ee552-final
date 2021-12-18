import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WikiTOC {
	BufferedReader br;
	
	WikiTOC(String s) {
		try { 
			br = new BufferedReader(new FileReader(s)); } 
		catch (IOException e) {
			e.printStackTrace(); }
	}
	
	public TableOfContents toTOC() {
		String line;
		TableOfContents tc;
		try {
		while((line = br.readLine()) != null) {
			if(line.contains("<h1 id=\"firstHeading\" class=\"firstHeading\">")) {
				tc = new TableOfContents(line.substring(line.indexOf("\">")+2, line.indexOf("</h1>")));
				getChapters(tc, 2);
				return tc;
			}
		}} catch (IOException e) {
			e.printStackTrace(); }
		return new TableOfContents("header not found");
	}
	
	public void getChapters(TableOfContents tc, int hLevel) {
		String line;
		try {
		while((line = br.readLine()) != null) {
			if(line.contains("<h" + (hLevel - 1) + ">") && line.contains("<span class=\"mw-headline\"")) {
				//System.out.println(line);
				tc.parent.add(line.substring(line.indexOf("\">", line.indexOf("class"))+2, line.indexOf("</span></h" + (hLevel - 1) + ">")));
				getChapters(tc.parent, hLevel - 1);
			}
			if(line.contains("<h" + hLevel + ">") && line.contains("<span class=\"mw-headline\"")) {
				//System.out.println(line);
				tc.add(line.substring(line.indexOf("\">", line.indexOf("class"))+2, line.indexOf("</span></h" + hLevel + ">")));
			}
			if(line.contains("<h" + (hLevel + 1) + ">") && line.contains("<span class=\"mw-headline\"")) {
				tc.get(tc.size()-1).add(line.substring(line.indexOf("\">", line.indexOf("class"))+2, line.indexOf("</span></h" + (hLevel + 1) + ">")));
				getChapters(tc.get(tc.size()-1), hLevel + 1);
			}
		}} catch (IOException e) {
			e.printStackTrace(); }
	}
	
}