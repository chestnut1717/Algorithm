import java.util.*;
import java.io.*;

public class Main {
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int[][] arr;
    static boolean[][] visited;
    static int N;
    static int maxHeight = Integer.MIN_VALUE;
    static int minHeight = Integer.MAX_VALUE;
    static int result = 0;
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		// 배열 입력
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        int tmp = Integer.parseInt(st.nextToken());
		        arr[i][j] = tmp; // 높이 입력
		        maxHeight = Math.max(maxHeight, tmp);
		        minHeight = Math.min(minHeight, tmp);
		        
		    }
		}
		
		visited = new boolean[N][N];
		for(int h = minHeight-1; h <= maxHeight; h++) {
		    int tmpResult = 0;
		    for(int i = 0; i < N; i++) {
		        for(int j = 0; j < N; j++) {
		            if(!visited[i][j] && arr[i][j] > h) {
		                dfs(i, j, h);
		                tmpResult++;
		            }
		        }
		    }
		    result = Math.max(result, tmpResult);
		    visited = new boolean[N][N];
		}
		
		System.out.println(result);
		
	}
	
	static void dfs(int y, int x, int h) {
	    visited[y][x] = true;
	    for(int i = 0; i < 4; i++) {
	        int ny = y + dy[i];
	        int nx = x + dx[i];
	        if(isIn(ny, nx) && !visited[ny][nx] && arr[ny][nx] > h) {
	            dfs(ny, nx, h);
	        }
	    }
	}
	
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < N;
	}
}
