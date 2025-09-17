import java.util.*;
import java.io.*;

public class Main
{
    static int N, K;
    static Pair[] result = new Pair[100004];
    static int[] visited = new int[100004];
    static Queue<Integer> q = new LinkedList<>();
    static Stack<Integer> stk = new Stack<>();
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
        
        
        visited[N] = 1;
		result[N] = new Pair(1, -1);
		q.offer(N);
		
		bfs();
		
		System.out.println(result[K].dist - 1);
    
    	// 아래는 경로출력
	    int pivot = K;
        
		while(pivot != -1) {
		    stk.push(pivot);
		    pivot = result[pivot].prev;
		    
		}
		
		while(!stk.isEmpty()) {
		    sb.append(stk.pop()).append(' ');
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void bfs() {
	    if (N == K) return;
	    
	    while(!q.isEmpty()) {
	        int now = q.poll();
	        int nowDist = result[now].dist;
	        int prev = result[now].prev;
	        
	        int[] moves = {now+1, now-1, now*2};
	        
	        for(int next: moves) {
	            if(next < 0 || next > 100000) continue;
	            
	            if(visited[next] == 0) {
	                visited[next] = 1;
	                result[next] = new Pair(nowDist+1, now);
	                q.offer(next);
	                
	                if(next == K) return;
	               
	            }
	            
	        }
	    }
	}
}

class Pair{
    int dist;
    int prev;
    public Pair(int dist, int prev) {
        this.dist = dist;
        this.prev = prev;
    }
}