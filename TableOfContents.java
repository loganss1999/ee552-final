import java.util.ArrayList;
import java.util.List;

public class TableOfContents {
	
	public String title;
	private String content;
	public TableOfContents parent;
	public List<TableOfContents> chapters;
	
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
	
	public void add(TableOfContents tc) {
		chapters.add(tc);
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
		return title;
	}
	
	public void writeContent(String con) {
		title = new String(con);
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
	
	
	
}