import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class TableOfContents {
	
	public String title;
	private String content = "";
	public TableOfContents parent;
	public List<TableOfContents> chapters;
	public List<TableOfContents> links;
	
	TableOfContents(String title) { //head of table
		this.title = title;
		parent = null;
		chapters = new ArrayList<TableOfContents>();
	}
	
	TableOfContents(String title, TableOfContents parent) { //not head, called by add
		this.title = title;
		this.parent = parent;
		chapters = new ArrayList<TableOfContents>();
	}
	
	public void add(String title) {
		chapters.add(new TableOfContents(title, this));
	}
	
	public TableOfContents addAndReturn(String title) { //adds and returns newly added TOC (good for adding content to new sections)
		TableOfContents temp = new TableOfContents(title, this);
		chapters.add(temp);
		return temp;
	}
	
	public void add(TableOfContents tc) {
		chapters.add(tc);
	}
	
	public void addLink(TableOfContents tc) {
		links.add(tc);
	}
	
	public void remove(int ch) {
		chapters.remove(ch);
	}
	
	public void remove(TableOfContents tc) {
		chapters.remove(tc);
	}
	
	public TableOfContents get(int pos) {
		return chapters.get(pos);
	}
	
	public TableOfContents getLink(int pos) {
		return links.get(pos);
	}
	
	public void promote() { 
		if(parent == null || parent.parent == null)
			System.out.println("Can not promote this chapter");
		else {
			parent.parent.add(this);
			parent.remove(this);
		}
	}
	
	public void demote(int par) {
		if(par >= parent.chapters.size())
			System.out.println("Can not demote to this chapter");
		else {
			parent.get(par).add(this);
			parent.remove(this);
		}
	}
	
	public String readContent() {
		return content;
	}
	
	public void printContent() {
		System.out.println(content);
	}
	
	public void deleteContent() {
		content = "";
	}
	
	public void writeContent(String con) {
		content = content.concat(con + '\n');
	}
	
	public int size() {
		return chapters.size();
	}
	
	public void print() {
		System.out.println(title);
		if(!(chapters.isEmpty())) {
			for(int i = 0; i < chapters.size(); i++) {
				System.out.print((i+1) + " ");
				chapters.get(i).print(0, (i+1) + ".");
			}
		}
	}
	
	public void print(int l, String name) {
		System.out.println(title);
		if(!(chapters.isEmpty())) {
			for(int i = 0; i < chapters.size(); i++) {
				for(int j = 0; j < l+1; j++) {
					System.out.print("\t");
				}
				System.out.print(name + (i+1) + " ");
				chapters.get(i).print(l + 1, name + (i+1) + ".");
			}
		}
	}
	
	BufferedWriter bw;
	
	public void toHTML(String path) {
		try {
		File f = new File(path);
		bw = new BufferedWriter(new FileWriter(f));
		bw.write("<html><body><h1>" + title + "</h1>");
		bw.write(content);
		for(TableOfContents chapter : chapters) {
			bw.write("<h2>" + chapter.title + "</h2>");
			bw.write("<hr size=\"1\" width=\"100%\" color=\"black\">");
			bw.write(chapter.content);
			for(TableOfContents subchapter : chapter.chapters) {
				bw.write("<h3>" + subchapter.title + "</h3>");
				bw.write(subchapter.content);
				for(TableOfContents subsubchapter : subchapter.chapters) {
					bw.write("<h4>" + subsubchapter.title + "</h4>");
					bw.write(subsubchapter.content);
				}
			}
		}
		}catch (IOException e) {
			e.printStackTrace(); }
	}
	
	public void toFile(String path) { //works best with txt, supports rtf
		try {
		File f = new File(path);
		bw = new BufferedWriter(new FileWriter(f));
		bw.write(title); bw.newLine();
		bw.write(content); bw.newLine();
		for(TableOfContents chapter : chapters) {
			bw.write(chapter.title); bw.newLine();
			bw.write(chapter.content); bw.newLine();
			for(TableOfContents subchapter : chapter.chapters) {
				bw.write("\t" + subchapter.title); bw.newLine();
				bw.write("\t" + subchapter.content); bw.newLine();
				for(TableOfContents subsubchapter : subchapter.chapters) {
					bw.write("\t\t" + subsubchapter.title); bw.newLine();
					bw.write("\t\t" + subsubchapter.content); bw.newLine();
				}
			}
		}
		bw.close();
		}catch (IOException e) {
			e.printStackTrace(); }
	}
	
	@Override
	public String toString() {
		return "";
	}
}