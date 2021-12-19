public class Main {
	public static void main(String[] args) {
		TableOfContents tc1 = new TableOfContents("Programming");
		tc1.writeContent("This is a guide to programming languages");
		tc1.add("C++");
		tc1.get(0).writeContent("This is a cool language");
		tc1.add("Python");
		tc1.add("Java");
		tc1.get(2).add("Versions");
		tc1.get(2).get(0).add("Java 8");
		tc1.get(2).get(0).add("Java 11");
		tc1.get(2).get(0).get(1).writeContent("11 is the best version");
		tc1.get(2).get(0).add("Java 17");
		tc1.get(2).add("Syntax");
		tc1.add("Golang");
		tc1.print();

		WikiTOC wiki = new WikiTOC("U.S. State - Wikipedia.html");
		TableOfContents tc2 = wiki.toTOC();
		tc2.print();
		tc1.toFile("output.txt");
		tc2.toHTML("output.html");
	}
}