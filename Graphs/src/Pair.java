

public class Pair implements Comparable<Pair> {
	private int[] vertices;
	private int egde;
	
	public Pair(int first, int second, int edge){
		vertices = new int[2];
		vertices[0] = first;
		vertices[1] = second;
		this.egde = edge;
	}
	
	public int getFirt(){
		return vertices[0];
	}
	
	public int getSecond(){
		return vertices[1];
	}
	
	public int getEdge(){
		return egde;
	}

	@Override
	public int compareTo(Pair o) {
		if(this.egde < o.getEdge())
			return -1;
		if(this.egde > o.getEdge())
			return 1;
		return 0;
		
	}

	

}
