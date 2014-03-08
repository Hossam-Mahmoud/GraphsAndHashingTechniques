
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;



public class DirGraph {
	private int[][] graph;

	public DirGraph(int[][] gr) {
		graph = gr;
	}

	public void BFS() {
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
			System.out.print((e+1) + ", ");
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
		System.out.print((node+1)+ ", ");
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
		String total ="";
		while(path != start){
			total = ", "+path +total;
			path = parent[path];
		}
		System.out.println("Shortest Path : "+start + total);
		System.out.println("Shortest Path cost : "+ dist[end]);
	}
	
	class comparePath implements Comparator<Integer>{
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

	public void countConnected() {
		boolean[] visit = new boolean[graph.length];
		Stack<Integer> stck = new Stack<Integer>();
		for(int i=0; i<graph.length ; i++){
			if(visit[i] == false)
				makeDFS(i, visit, stck);
		}
		int count=0;
		boolean[] visit2 = new boolean[graph.length];
		while(!stck.isEmpty()){
			count++;
			int node = stck.pop();
			makeDFS2(node,visit2,stck);
			for (Iterator<Integer> it = stck.iterator(); it.hasNext();) {
				if (visit2[it.next()])
					it.remove();
			}
			
		}
		System.out.println("Connected components : "+count);
	}
	
	private void makeDFS2(int node, boolean[] visit, Stack<Integer> stck){
		visit[node] = true;
		for(int i=0; i<graph[node].length; i++){
			if(graph[node][i] != 0 && visit[i] == false){
				makeDFS(i, visit, stck);
			}
			
		}
		
	}
	
	private void makeDFS(int node, boolean[] visit, Stack<Integer> stck){
		visit[node] = true;
		stck.push(node);
		for(int i=0; i<graph[node].length; i++){
			if(graph[node][i] != 0 && visit[i] == false){
				makeDFS(i, visit, stck);
			}
			
		}
	}
	
	public void topologicalSort(){
		Queue<Integer> intialNodes = new LinkedList<Integer>();
		Queue<Integer> sorted = new LinkedList<Integer>();
		int[] edges = new int[graph.length];
		for(int i=0; i<graph.length; i++){
			intialNodes.add(i);
		}
		for(int i=0; i<graph.length; i++){
			for(int j=0; j<graph[i].length; j++){
				if(graph[i][j] != 0)
					edges[j]++;
				if(graph[i][j] != 0 && intialNodes.contains(j))
					intialNodes.remove(j);
			}
		}
		while(!intialNodes.isEmpty()){
			int node = intialNodes.poll();
			sorted.add(node);
			for(int i=0; i<graph[node].length; i++){
				if(graph[node][i] != 0)
					edges[i]--;
				if(graph[node][i] != 0 && edges[i] == 0)
					intialNodes.add(i);
			}
		}
		while(!sorted.isEmpty())
			System.out.print(sorted.poll()+", ");
		System.out.println();
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
				if(graph[node][j] != 0){
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
		DirGraph gr = new DirGraph(arr);
		gr.countConnected();
		System.out.println("cycles : ");
		gr.printCycles();
		
		
	}

}
