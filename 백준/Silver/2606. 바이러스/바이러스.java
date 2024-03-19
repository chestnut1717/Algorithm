import java.io.*;
import java.util.*;

public class Main
{
    static int N, K;
    static ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
    static boolean[] visited;
    static Queue<Integer> q = new ArrayDeque<>();
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i< N+1;i++) {
		    lst.add(new ArrayList<>());
		}
		
		visited = new boolean[N+1];
		
		for(int i = 0; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int from = Integer.parseInt(st.nextToken());
		    int to = Integer.parseInt(st.nextToken());
		    
		    lst.get(from).add(to);
		    lst.get(to).add(from);
		}
		
		insertQ(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
		    int n = q.poll();
		    if(!visited[n]) {
		        visited[n] = true;
		        result++;
		        insertQ(n);
		    }
		    
		    
		}
		
		System.out.println(result);

	}
	
	static void insertQ(int n) {
	    for(int num: lst.get(n)) {
	        if(!visited[num]) {
	            q.offer(num);
	        }
	    }
	}
}
