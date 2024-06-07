import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 징검다리 개수
    static int[] arr;
    static int start, end;
    static int[] dp; // dp는 i번째 징검다리를 갈 수 있는 최소 거리
    static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		// 이제 징검다리 적힌 수 입력
		arr = new int[N+1]; // 1부터 시작해야 배수 계산하기 편함
		dp = new int[N+1];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 시작점, 종점 입력
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		// 시작점과 종점이 같으면
        if (start == end) {
            System.out.println(0);
        } else {
            bfs();
        }
		
		
	}
	
	static void bfs() {
	    visited = new boolean[N+1];
	    Queue<int[]> q = new ArrayDeque<>();
	    visited[start] = true;
	    q.add(new int[] {start, 0});
	    
	    // bfs 시작
	    while(!q.isEmpty()) {
	        int[] now = q.poll();
	        int baesu = arr[now[0]];
	        int next = now[0];
	        
	        // 양의 방향으로 이동
	        while(true) {
	            next += baesu;
	            if(next > N) break; // 혹시라도 범위를 벗어나버리면
	            if(visited[next]) continue;
	            visited[next] = true;
	            
	            if(next == end) {
	                System.out.println(now[1] + 1); // 총 이동 거리 출력
	                return;
	            }
	            q.add(new int[] {next, now[1] + 1});
	        }
	        
	        next = now[0];
	        // 음의 방향으로 이동
	        while(true) {
	             next -= baesu;
	             if(next < 1) break;
	             if(visited[next]) continue;
	             visited[next] = true;
	             
	             if(next == end) {
	                System.out.println(now[1] + 1); // 총 이동 거리 출력
	                return;
	            }
	            q.add(new int[] {next, now[1] + 1});
	        }
	    }
	    // 도달하지 못했을 때
	    System.out.println(-1);
	}
	
	

}
