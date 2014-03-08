package Hash;
import java.util.Random;



public class PRHashingTable extends Hash {
	private Random rand;
	
	public PRHashingTable(int size){
		SIZE = (size*2)+7;
		table = new Symbol[SIZE];
		records=0;
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
		int i=0;
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
				if(table[pos].getName().equals("$") )
					break;
				i++;
				pos = (start+getNextRandom(i))%SIZE;
				if(pos == start || i>SIZE){
					enlarge();
					inserted = false;
					break;
				}
				
			}
		}
		if(inserted){
			table[pos] = sym;
			records++;
			collision += i;
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
			int i=1;
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
				pos =(start+getNextRandom(i))%SIZE;
				if(start == pos){
					deleted = false;
					break;
				}
				i++;
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
		int i=1;
		while(table[pos] != null){
			if(table[pos].getName().equals(sym)){
				found = true;
				break;
			}
			pos = (start+getNextRandom(i))%SIZE;
			if(start == pos)
				break;
			i++;
		}
		if(found)
			return i;
		else 
			return 0;
	}
	
	/**
	 * To generate a random number with a fixed seed
	 * @param i to get the next random number
	 * 
	 * @return
	 */
	private int getNextRandom(int i){
		rand = new Random(SIZE);
		return ((rand.nextInt(i)+5)*i);
		
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
