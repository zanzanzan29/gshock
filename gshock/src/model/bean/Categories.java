package model.bean;

public class Categories {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categories(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
}
