import java.util.*;
import java.io.*;

public class Solution
{
    static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    static int T;
    static int N;
    static int[][] arr; // N * N 크기
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		// 각 테스트 케이스만큼 진행
		
		for(int t = 1; t <= T; t++) {
		    N = Integer.parseInt(br.readLine());
		    
		    // 각 열 입력
		    arr = new int[N][N];
		    for(int i = 0; i < N; i++) {
		        String row = br.readLine();
		        for(int j = 0; j < N; j++) {
		            // 지뢰 없는 칸 : -1, 지뢰 : -99
		            arr[i][j] = row.charAt(j) == '.' ? -1 : -99;
		        }
		    }
		    
		    result = 0;
		    // 이제 각각의 arr에 대해 click을 한다.
		    for(int i = 0; i < N; i++) {
		        for(int j = 0; j < N; j++) {
		            // 이미 클릭된 곳이거나 지뢰이면 무시
		            if(arr[i][j] != -1) continue;
		            if(isZero(i, j)) {
		                result++;
		                click(i, j);
		            }
		        }
		    }
		    
    		for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == -1) result++;
                }
            }
		    
		    System.out.printf("#%d %d\n", t, result);
		    
		    
		    
		    
		}
	}
	// 우선 8방탐색을 해서 모두 zero이면 더 확장할 수 있으므로 ㄱ
	static boolean isZero(int y, int x) {
	    for(int i = 0; i < 8; i++) {
	        int ny = y + dy[i];
	        int nx = x + dx[i];
	        if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
	        if(arr[ny][nx] == -99) return false;
	        
	    }
	    return true;
	}
	
	static void click(int y, int x) {
	    Queue<int[]> q = new ArrayDeque<>();
	    q.offer(new int[] {y, x});
	    arr[y][x] = 0;
	    
	    while(!q.isEmpty()) {
	        int[] now = q.poll();
	        int nowY = now[0];
	        int nowX = now[1];
	        arr[y][x] = 0;
	        
	        for(int i = 0; i < 8; i++) {
	            int ny = nowY + dy[i];
	            int nx = nowX + dx[i];
	            // 유효한 범위이고 이미 방문한 곳이면 무시
	            if(ny < 0 || ny >= N || nx < 0 || nx >= N || arr[ny][nx] != -1) continue;
	            if(isZero(ny, nx)) {
	                q.offer(new int[] {ny, nx});
	            }
	            arr[ny][nx] = 0;
	        }
	    }
	}
}