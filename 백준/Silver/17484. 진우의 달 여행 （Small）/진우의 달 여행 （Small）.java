import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dx = {-1, 0, 1};
    static int N, M;
    static int[][] map;
    static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		for(int i = 0; i < M; i++) {
		    dfs(0, i, -1, 0);
		    
		}
		
		System.out.println(result);
		
	}

	static void dfs(int y, int x, int prev, int sum) {
	    if(y==N) {
	        result = Math.min(result, sum);
	        return;
	    }
	    // 3방향 중 탐색
	    for(int i = 0; i < 3; i++) {
	        if(prev == i) continue;
	        int nx = x + dx[i];
	        if(nx >= 0 && nx < M) {
	            
	            dfs(y+1, nx, i, sum + map[y][x]);
	            
	        }
	    }
	}
	
	static boolean isIn(int x) {
	    return 0 <= x && x < M;
	}
}
