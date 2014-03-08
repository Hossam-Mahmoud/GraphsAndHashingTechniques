package Hash;

public abstract class Hash {
	public int SIZE;
	public Symbol[] table;
	public int records;
	public int collision;
	public abstract void insert(Symbol sym) throws Exception;
	public abstract void delete(String sym) throws Exception;
	public abstract int Search(String sym);
	public abstract double getAverage();
	
	/**
	 *  The pattern to print the hash table
	 * @return
	 */
	public String print(){
		String t ="";
		for(int i=0; i<table.length; i++){
			if(table[i] != null){
				t += "["+table[i].getName()+"]";
				Symbol pntr = table[i].getNext();
				while(pntr != null){
					t += "->["+pntr.getName()+"]";
					pntr = pntr.getNext();
				}
				t += "\n";
			}
			else
				t += "[]\n";
		}
		return t;
	}
	public void clear() {
		SIZE =0;
		table = null;
	}
}
