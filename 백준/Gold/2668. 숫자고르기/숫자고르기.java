import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[] nums;
    static boolean[] visited; // tracking 방문여부 체크
    static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuilder sb = new StringBuilder();
	    
		N = Integer.parseInt(br.readLine());
		
		// 입력
		nums = new int[N+1];
	    visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
		    nums[i] = Integer.parseInt(br.readLine());
		}
		
		// 이제 dfs를 통해 cycle을 탐지한다.
		for(int now = 1; now <= N; now++) {
		    // 이미 연결되어 있는다음 노드가 방문되어 있다면 무시
		    visited[now] = true;
		    dfs(now, now);
		    visited[now] = false;
		}
		
		Collections.sort(list);
		
		sb.append(list.size()).append('\n');
		for(int num: list) {
		    sb.append(num).append('\n');
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
	
	static void dfs(int start, int target) {
		if(visited[nums[start]] == false) {
		    visited[nums[start]] = true;
		    dfs(nums[start], target);
		    visited[nums[start]] = false;
		}
		if(nums[start] == target) list.add(target);
	    
	}
}