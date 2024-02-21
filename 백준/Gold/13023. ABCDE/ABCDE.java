import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static List<List<Integer>> arr = new ArrayList<>();
    static boolean[] visited;
    static int result = 0;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    // arr 초기화
	    for(int i = 0; i < N; i++) {
	        arr.add(new ArrayList<>());
	    }
	    
	    visited = new boolean[N]; //visited 초기화
	    for(int i = 0; i < M; i++) {
	        st = new StringTokenizer(br.readLine());
	        int tmp1 = Integer.parseInt(st.nextToken());
	        int tmp2 = Integer.parseInt(st.nextToken());
	        
	        // 인접리스트에 서로 넣어줌
	        arr.get(tmp1).add(tmp2);
	        arr.get(tmp2).add(tmp1);
	        
	    }
	    
	    
	    // dfs를 통해 계속 연결되는지 확인한다.
	    for(int i = 0; i < N; i++) {
	        visited[i] = true;
	        dfs(i, 0);
	        visited[i] = false;
	        if (result == 1) break;
	    }
	    
	    System.out.println(result);
	}
	
	static void dfs(int now, int cnt) {
	    if(cnt == 4) { // 5명이 관계를 맺으면 연속으로 연결된 간선은 4개
	        result = 1;
	        return;
	    }
	    for(int next: arr.get(now)) {
    	    if(!visited[next]) {
    	        visited[next] = true;
    	        dfs(next, cnt+1);
    	        visited[next] = false;
    	    }
	    }

	}

}
