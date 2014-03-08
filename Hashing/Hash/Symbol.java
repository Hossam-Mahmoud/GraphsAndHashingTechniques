package Hash;

public class Symbol {
	private String name;
	private String type;
	private int address;
	private String scope;
	private int length;
	private Symbol next;
	
	
	//this class is just some setters and getters for the instance variables of the 
	//Symbol class
	public Symbol(String nm , String tp, int adrs, String scp, int lngth){
		name = nm;
		type = tp;
		address = adrs;
		scope = scp;
		length = lngth;
		next = null;
	}
	
	public void setName(String nm){
		name = nm;
	}
	
	public void setType(String typ){
		type = typ;
	}
	public void setAddress(int adrs){
		address = adrs;
	}
	public void setScope(String scp){
		scope = scp;
	}
	public void setLength(int lngth){
		length = lngth;
	}
	
	public void setNext(Symbol nxt){
		next = nxt;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public String getScope(){
		return scope;
	}
	
	public int getAddress(){
		return address;
	}
	
	public int getLength(){
		return length;
	}
	
	public Symbol getNext(){
		return next;
	}

}
