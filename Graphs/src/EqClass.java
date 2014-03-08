import java.util.Hashtable;


public class EqClass {
	
	private int[] table;
	private int[] parent;
	private Hashtable<Integer, Integer> hTable;
	
	public EqClass(int[] tab){
		table = tab;
		parent = new int[table.length];
		hTable = new Hashtable<Integer, Integer>();
		for(int i=0; i<table.length; i++){
			parent[i]=-1;
			hTable.put(table[i], i);
		}
		
	}
	
	public void Merge(int a, int b){
		int posA=-1;
		if(hTable.containsKey(a))
			posA = hTable.get(a);
		
		int posB=-1;
		if(hTable.containsKey(b))
			posB = hTable.get(b);
		
		
		if(posA != -1 && posB != -1){
			int root1 = find(posA);
			int root2 = find(posB);
			if(root1 != root2)
				parent[root2] = root1;
		}
	}
	
	private int find(int a){
		if(parent[a] == -1)
			return a;
		else{
			while(parent[a] != -1 )
				a = parent[a];
			return a;
		}
	}
	
	public boolean relative(int a, int b){
		int posA=-1;
		if(hTable.containsKey(a))
			posA = hTable.get(a);
		
		int posB=-1;
		if(hTable.containsKey(b))
			posB = hTable.get(b);
		
		if(posA !=-1 && posB != -1){
			int root1 = find(posA);
			int root2 = find(posB);
			return (root1 == root2);
		}else
			return false;
	}
	
	
	public static void main(String[] args) {
		

	}

}
