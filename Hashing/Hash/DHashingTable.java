package Hash;


public class DHashingTable extends Hash {
	
	private int CONST;
	
	public DHashingTable(int size){
		SIZE = size+7;
		table = new Symbol[SIZE];
		CONST = SIZE/2;
		records =0;
		collision =0;
	}
	
	
	/**
	 * Insert a new record in the the hash table, if the hash is nearly full it will be
	 * became larger, if the record inserted before ,this one will not inserted
	 * @param
	 * 		 sym : the symbol that should be inserted
	 * 
	 */
	public void insert(Symbol sym) throws Exception{
		int pos = firstHash(sym.getName());
		int offset = secondHash(sym.getName());
		int start = pos;
		int count=0;
		boolean inserted = false;
		if(table[pos] == null){
			inserted = true;
		}else{
			inserted = true;
			while(table[pos] != null){
				if(table[pos].getName().equals(sym.getName())){
					inserted = false;
					break;
				}
				if(table[pos].getName().equals("$")){
					inserted = true;
					break;
				}
				pos = (pos+offset)%SIZE;
				count++;
				if(pos == start || count>SIZE){
					inserted = false;
					enlarge();
					break;
				}
			}
		}
		if(inserted){
			table[pos] = sym;
			records++;
			collision += count;
			double factor = records/SIZE;
			if(factor > 0.5){
				enlarge();
			}
		}
	}
	
	/**
	 *  To enlarge the array
	 */
	private void enlarge() throws Exception{
		Symbol[] tab = new Symbol[SIZE*2];
		Symbol[] tmp = table;
		table = tab;
		SIZE = SIZE*2;
		CONST = CONST/2;
		records=0;
		for(int i=0; i<table.length; i++){
			if(tmp[i] != null){
				if(! (tmp[i].getName().equals("$"))){
					insert(tmp[i]);
				}
			}
		}
	}
	
	/**
	 * return the average collision
	 * 
	 */
	public double getAverage(){
		double c = collision;
		double r = records;
		return c/r;
	}
	
	/**
	 * Delete the specified record with the String given as a parameter
	 */
	public void delete(String sym) throws Exception{
		int pos = firstHash(sym);
		int offset = secondHash(sym);
		if(table[pos] == null)
			throw new Exception("Not found !");
		else{
			int start = pos;
			boolean deleted = false;
			int count = 1;
			while(table[pos] != null){
				if(table[pos].getName().equals(sym)){
					Symbol delete = new Symbol("$", null, -1, null, 0);
					table[pos] = delete;
					deleted = true;
					break;
				}
				pos =(pos+offset)%SIZE;
				if(start == pos || count >SIZE){
					deleted = false;
					break;
				}
			}
			if(!deleted)
				throw new Exception("Not found !");
		}	
	}
	
	/**
	 * Search the hash table for a symbol
	 */
	public int Search(String sym){
		int pos = firstHash(sym);
		int offset = secondHash(sym);
		int start = pos;
		boolean found = false;
		int count =1;
		while(table[pos] != null){
			if(table[pos].getName().equals(sym)){
				found = true;
				break;
			}
			pos = (start+offset)%SIZE;
			if(start == pos || count>SIZE)
				break;
			count++;
		}
		if(found)
			return count;
		else
			return 0;
	}
	/**
	  * method to get the hash code
	  * @param sym	A string to get the hash value from it 
	  * @return the hash value
	  */
	private int firstHash(String sym){
		int firstAscii = sym.charAt(0);
		int lasAscii = sym.charAt(sym.length() - 1);
		int midAscii = sym.charAt(sym.length()/2);
		int pos = ((firstAscii + lasAscii)*midAscii) % SIZE;
		return pos;
	}
	/**
	 *  The second hash value;
	 * @param sym
	 * @return
	 */
	private int secondHash(String sym){
		int firstAscii = sym.charAt(0);
		int lasAscii = sym.charAt(sym.length() - 1);
		int midAscii = sym.charAt(sym.length()/2);
		int key = ((firstAscii + lasAscii) * midAscii)%SIZE;
		int pos = CONST - (key%CONST);
		return pos;
	}
}
