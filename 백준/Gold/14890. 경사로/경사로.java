import java.io.*;
import java.util.*;

public class Main
{
    static int N, L;
    static int[][] map;
    static int result;
    // static Stack<Integer> stk = new Stack<>(); // 높이 넣어줄 스택 정의
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		// 입력받음
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// 행 검사
		for(int row = 0; row < N; row++) {
		    int[] tmp = map[row];
		    if(checkRow(tmp)) {
		        result++;
		    }
		}
		
		// 열 검사
		for(int col = 0; col < N; col++) {
		    int[] tmp = new int[N];
		    for(int y = 0; y < N; y++) {
		        tmp[y] = map[y][col];
		    }
		    if(checkRow(tmp)) {
		        result++;
		    }
		}
		
		// 정답
		System.out.println(result);
		
	}
	static boolean checkRow(int[] road) {
	    int prev = 0;
	    int now = 1;
	    boolean[] visited = new boolean[N];
	    while(now < N) {
	        if(road[prev] == road[now]) {
	            prev++;
	            now++;
	        }
	        // 내리막
	        else if(road[prev] == road[now] + 1) {
	            visited[now] = true;
	            prev++;
	            now++;
	            int cnt = 0;
	            while(cnt < L-1) {
	                if(prev >= N || now >= N) return false;
	                if(road[prev] != road[now]) return false;
                    visited[now] = true;
                    prev++;
                    now++;
                    cnt++;
	            }

	        }
	        // 오름차순
	        else if (road[prev] == road[now]-1) {
	            // 이전 값까지 돌아가서
	            for(int i = now-L; i <= prev; i++) {
	                if(i < 0) return false;
	                if(road[prev] != road[i]) return false;
	                if(visited[i]) return false;
	            }
                visited[prev] = true; // 방문처리 해주고
                prev++;
                now++;
	        }
	        else {
	            return false;
	        }
       
	    }
	    return true;

	}	

	
}
