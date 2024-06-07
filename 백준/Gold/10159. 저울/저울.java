import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 물건 개수
    static int M; // 미리 측정 후보군
    static List<List<Integer>> bigger = new ArrayList<>(); // i보다 큰 후보군만 저장하는 배열
    static List<List<Integer>> smaller = new ArrayList<>();; // i보다 작은 후보군만 저장하는 배열
    static boolean[] visited;
    static int cnt;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    N = Integer.parseInt(br.readLine());
	    M = Integer.parseInt(br.readLine());
	    
        
        // 리스트 초기화	    
	    for(int i = 0; i < N+1; i++) {
	        bigger.add(new ArrayList<>());
	        smaller.add(new ArrayList<>());
	    }
        
	    // 이제 M개의 후보군 입력
	    for(int i = 0; i < M; i++) {
	        st = new StringTokenizer(br.readLine());
	        int big = Integer.parseInt(st.nextToken());
	        int small = Integer.parseInt(st.nextToken());
	        
	        bigger.get(small).add(big);
	        smaller.get(big).add(small);
	        
	    }
	    
	    
		// 이제 각각의 숫자마다 하나씩 탐색을 하면서 방문여부까지 검사한다.
		for(int i = 1; i <= N; i++) {
		    visited = new boolean[N+1];
		    cnt = N-1; // 가능하지 못한 경우, 자기 자신은 제외
		    dfs_big(i);
		    dfs_small(i);
		    
		    sb.append(cnt).append('\n');
		}
		
		bw.write(sb.toString());
		bw.close();
	}
	
	static void dfs_big(int start) {
	    for(int next: bigger.get(start)) {
	        if(visited[next]) continue;
	        visited[next] = true;
	        cnt -= 1;
	        dfs_big(next);
	    }
	}

	static void dfs_small(int start) {
	    for(int next: smaller.get(start)) {
	        if(visited[next]) continue;
	        visited[next] = true;
	        cnt -= 1;
	        dfs_small(next);
	    }
	}
}
