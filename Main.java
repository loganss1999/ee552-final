public class Main {
	public static void main(String[] args) {
		// TableOfContents tc = new TableOfContents("Contents");
		// tc.add("states");
		// tc.add("background");
		// tc.add("governments");
		// tc.get(2).add("constitutions");
		// tc.get(2).get(0).add("executive");
		// tc.get(2).get(0).add("legislative");
		// tc.get(2).get(0).add("judicial");
		// tc.get(2).add("states as unitary systems");
		// tc.add("relationships");
		// tc.print();
		
		WikiTOC wiki = new WikiTOC("Puerto Rico - Wikipedia.html");
		TableOfContents tc = wiki.toTOC();
		tc.print();
	}
}