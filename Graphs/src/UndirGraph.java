import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class UndirGraph {
	private int[][] graph;
	
	public UndirGraph(int[][] gr){
		graph = gr;
		for (int i=0; i<gr.length; i++){
			for(int j=0; j<gr[i].length; j++){
				if(gr[i][j] !=0){
					int val = gr[i][j];
					graph[j][i] = val;
				}
			}
		}
	}
	
	public void BFS(){
		Queue<Integer> qe = new LinkedList<Integer>();
		boolean[] visit = new boolean[graph.length];
		qe.add(0);
		visit[0] = true;
		System.out.print("BFS : ");
		while(!qe.isEmpty()){
			int e = qe.poll();
			for(int i=0; i<graph[e].length; i++){
				if(graph[e][i] != 0 && visit[i] == false){
					qe.add(i);
					visit[i]= true;
				}
			}
			System.out.print(e + ", ");
		}
		System.out.println();
		
	}
	
	public void DFS(int node, boolean[] visit){
		if(visit == null){
			visit = new boolean[graph.length];
			visit[0] = true;
		}
		for(int i=0; i<graph[node].length; i++){
			if(graph[node][i] !=0 && visit[i] == false){
				visit[i] = true;
				DFS(i, visit);
			}
		}
		System.out.print(node+ ", ");
	}
	
	public void Dijekstra(int start, int end){
		int[] dist = new int[graph.length];
		for(int i=0; i<graph.length; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
		int[] parent = new int[graph.length];
		Comparator<Integer> comp = new comparePath(dist);
		PriorityQueue<Integer> qu = new PriorityQueue<Integer>(graph.length, comp);
		for(int i=0; i<graph.length; i++){
			qu.add(i);
		}
		while(!qu.isEmpty()){
			int e = qu.poll();
			if(e == end){
				break;
			}
			for(int i=0; i<graph[e].length; i++){
				if(graph[e][i] != 0){
					int newDist = dist[e]+graph[e][i];
					if(newDist < dist[i]){
						dist[i] = newDist;
						parent[i] = e;
						if(qu.contains(i))
							qu.remove(i);
						qu.add(i);
					}
				}
			}
		}
		int path = end;
		String total = "";
		while(path != start){
			total = ", "+path +total;
			path = parent[path];
		}
		System.out.println("Shortest Path : "+start + total);
		System.out.println("Shortest Path cost : "+ dist[end]);

	}
	
	private class comparePath implements Comparator<Integer>{
		private int[] path;
		public comparePath(int[] arr){
			path = arr;
		}
		@Override
		public int compare(Integer o1, Integer o2) {
			if(path[o1] < path[o2])
				return -1;
			if(path[o1] > path[o2])
				return 1;
			return 0;
		}
		
	}
	
	public void prims(int start){
		int[] dist = new int[graph.length];
		for(int i=0; i<dist.length; i++){
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		comparePath comp = new comparePath(dist);
		boolean[] visit = new boolean[graph.length];
		int[] parent = new int[graph.length];
		for(int i=0; i<parent.length; i++){
			parent[i] = -1;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(graph.length,comp );
		pq.add(start);
		while(!pq.isEmpty()){
			int node = pq.poll();
			visit[node] = true;
			for(int i=0; i<graph[node].length; i++){
				if(graph[node][i] != 0 && visit[i] == false){
					if(graph[node][i] < dist[i]){
						dist[i] = graph[node][i];
						parent[i] = node;
						if(pq.contains(i))
							pq.remove(i);
						pq.add(i);
					}
				}
			}
		}
		int Mst =0;
		System.out.println("Prim's minimum spanning tree for node ("+start+") : ");
		for(int i=0; i<parent.length; i++){
			if(visit[i] && parent[i] != -1){
				System.out.println(parent[i]+"<-"+i);
				Mst += dist[i];
			}
		}
		System.out.println("Prim's MST = " + Mst);
	}
	
	public void kruskal(){
		int[] table = new int[graph.length];
		for(int i=0; i<table.length; i++)
			table[i] = i;
		EqClass check = new EqClass(table);
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		for(int i=0; i<graph.length; i++){
			for(int j=i; j<graph[i].length; j++){
				if(graph[i][j] != 0)
					pq.add(new Pair(i, j, graph[i][j]));
			}
		}
		ArrayList<Pair> result = new ArrayList<Pair>();
		int MST = 0;
		while(!pq.isEmpty()){
			Pair p = pq.poll();
			if(!check.relative(p.getFirt(), p.getSecond())){
				MST += p.getEdge();
				result.add(p);
				check.Merge(p.getFirt(), p.getSecond());
			}
		}
		System.out.println("kruskal MST : "+MST);
		System.out.println("Kruskal Tree : ");
		for(Pair o : result){
			System.out.println(o.getFirt()+ "<-"+ o.getSecond());
		}
	}
	
	public void countConnected(){
		Queue<Integer> all = new LinkedList<Integer>();
		for(int i=0; i<graph.length; i++)
			all.add(i);
		int count=0;
		while(!all.isEmpty()){
			count++;
			int node = all.poll();
			finish(node, all);
		}
		System.out.println(count);
	}
	
	private void finish(int n, Queue<Integer> nodes){
		for(int i=0; i<graph[n].length; i++){
			if(graph[n][i] !=0 && nodes.contains(i)){
				nodes.remove(i);
				finish(i, nodes);
			}
		}
	}
	
	private void cycles(int node , boolean[] visited, String cycle, boolean[] toCheckOut, int prev){
		if(visited[node] == true){
			String x = ""+node;
			String tmp = cycle.substring(cycle.indexOf(x));
			System.out.println(tmp );
		}else{
			boolean[] visit = new boolean[visited.length];
			System.arraycopy(visited, 0, visit, 0, visited.length);
			visit[node] = true;
			toCheckOut[node] = true;
			for(int j=0; j<graph[node].length; j++){
				if(graph[node][j] != 0 && j != prev){
					cycles(j, visit, cycle+","+j, toCheckOut, node);
				}
			}
		}
	}
	
	public void printCycles(){
		boolean[] check = new boolean[graph.length];
		boolean[] visited = new boolean[graph.length];
		for(int i=0; i<graph.length; i++){
			if(check[i] != true){
				check[i] = true;
				cycles(i, visited, ""+i, check, -1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[][] arr = new int[size][size];
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				int vl = sc.nextInt();
				arr[i][j] = vl;
			}
		}
		UndirGraph gr = new UndirGraph(arr);
		//gr.BFS();
		//System.out.print("DFS : ");
		//gr.DFS(0, null);
		//System.out.println();
		//gr.Dijekstra(0, 3);
		//gr.prims(0);
		//gr.kruskal();
		//gr.printCycles();
		//gr.countConnected();
		gr.printCycles();
		gr.countConnected();
	}
	
}




