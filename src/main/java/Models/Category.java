package Models;

public class Category {
	
	// variable names which are same as names in database
	private int id;
	private String name;
	private int business_id;
	private String business_name;
	
	public int getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	
	// and then generated getter and setter 
	// methods in order to manipulate with database
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
	

}
