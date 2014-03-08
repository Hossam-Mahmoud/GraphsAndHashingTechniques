package Hash;

public class ChainHashingTable extends Hash {


	public ChainHashingTable(int size) {
		SIZE = (size*2)+7;
		table = new Symbol[SIZE];
		records = 0;
		collision =0;
	}
	/**
	 * Insert a new record in the the hash table, if the hash is nearly full it will be
	 * became larger, if the record inserted before ,this one will not inserted
	 * @param
	 * 		Symbol sym : the symbol that should be inserted
	 * 
	 */
	public void insert(Symbol sym){
		int pos = hashValue(sym.getName());
		if(table[pos] == null){
			table[pos] = sym;
			records++;
		}else{
			Symbol pntr = table[pos];
			boolean inserted = true;
			while(pntr.getNext()!= null){
				if(pntr.getName().equals(sym.getName())){
					inserted = false;
					break;
				}
				pntr = pntr.getNext();
				collision++;
			}
			if(inserted){
				pntr.setNext(sym);
				records++;
			}
			double factor = records/SIZE;
			if(factor>0.9){
				enlarge();
			}
		}
	}
	
	
	/**
	 *  To enlarge the array
	 */
	private void enlarge(){
		
		Symbol[] tab = new Symbol[SIZE*2];
		Symbol[] tmp = table;
		table = tab;
		SIZE = SIZE*2;
		records=0;
		for(int i=0; i<table.length; i++){
			if(tmp[i] != null){
				insert(tmp[i]);
				Symbol pntr = tmp[i].getNext();
				while(pntr != null){
					insert(pntr);
					pntr = pntr.getNext();
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
	public void delete(String sym) throws Exception {
		int pos = hashValue(sym);
		if(table[pos] == null)
			throw new Exception("Not found");
		else{
			boolean found = false;
			Symbol pntr = table[pos];
			int i =0;
			while(pntr!= null){
				if(pntr.getName().equals(sym)){
					found = true;
					break;
				}
				pntr = pntr.getNext();
				i++;
			}
			if(found){
				if(i==0){
					table[pos] = table[pos].getNext();
				}else{
					Symbol prev = table[pos];
					for(int j=1; j<i; j++){
						prev = prev.getNext();
					}
					
					prev.setNext(pntr.getNext());
				}
			}else
				throw new Exception("Not found");
		}
	}
	
	/**
	 * Search the hash table for a symbol
	 */
	 public int Search(String sym){
		 int pos = hashValue(sym);
		 if(table[pos] == null)
			return 0;
		 else{
			boolean found = false;
			Symbol pntr = table[pos];
			int count =1;
			while(pntr!= null){
				if(pntr.getName().equals(sym)){
					found = true;
					break;
				}
				pntr = pntr.getNext();	
				count++;
			}
			if(found)
				return count;
			else
				return 0;
		 } 
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
