package Hash;


public class LPHashingTable extends Hash {
	
	
	public LPHashingTable(int size){
		SIZE = (size*2)+7;
		table = new Symbol[SIZE];
		records =0;
		collision =0;
	}
	
	
	/**
	 * Insert a new record in the the hash table, if the hash is nearly full it will be
	 * became larger, if the record inserted before ,this one will not inserted
	 * @param
	 * 		Symbol sym : the symbol that should be inserted
	 * 
	 */
	public void insert(Symbol sym) throws Exception{
		int pos = hashValue(sym.getName());
		int start = pos;
		boolean inserted = false;
		if(table[pos] == null){
			inserted = true;
		}else{
			inserted = true;
			while(table[pos] != null ){
				if(table[pos].getName().equals(sym.getName())){
					inserted = false;
					break;
				}
				if(table[pos].getName().equals("$")){
					inserted = true;
					break;
				}
				pos = (pos+1)%SIZE;
				collision++;
				if(pos == start){
					inserted = false;
					break;
				}
			}
		}
		if(inserted){
			table[pos] = sym;
			records++;
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
		int pos = hashValue(sym);
		if(table[pos] == null)
			throw new Exception("Not found !");
		else{
			int start = pos;
			boolean deleted = false;
			while(table[pos] != null)
			{
				if(table[pos].getName().equals(sym))
				{
					Symbol delete = new Symbol("$", null, -1, null, 0);
					table[pos] = delete;
					deleted = true;
					break;
				}
				pos =(pos+1)%SIZE;
				if(start == pos)
				{
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
		int pos = hashValue(sym);
		int start = pos;
		boolean found = false;
		int count = 1;
		while(table[pos] != null)
		{
			if(table[pos].getName().equals(sym))
			{
				found = true;
				break;
			}
			pos = (pos+1)%SIZE;
			if(start == pos)
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
	private int hashValue(String sym) {
		int firstAscii = sym.charAt(0);
		int lasAscii = sym.charAt(sym.length() - 1);
		int midAscii = sym.charAt(sym.length()/2);
		int pos = ((firstAscii + lasAscii) * midAscii)%SIZE;
		return pos;
	}
}
