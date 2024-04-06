import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int R, C; // 행, 열
    static char[][] map;
    static boolean[][] visited;
    static boolean[] checkAlpha = new boolean[26]; // 알파벳 체크할 수 있도록 도와줌
    static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    R = Integer.parseInt(st.nextToken());
	    C = Integer.parseInt(st.nextToken());
	    
	    map = new char[R][C];
	    visited = new boolean[R][C];
	    
	    // 각 맵에 값들을 입력
	    for(int i = 0; i < R; i++) {
	        String str = br.readLine();
	        for(int j = 0; j < C; j++) {
	            map[i][j] = str.charAt(j);
	        }
	    }
	    
	    // 시작점, 끝점 설정
	    int startY = 0;
	    int startX = 0;
	    
	    visited[startY][startX] = true;
	    checkAlpha[map[startY][startX] - 'A'] = true;
        dfs(startY, startX, 1);
	    
	    System.out.println(result);
	    
	}
	
	static void dfs(int y, int x, int cnt) {
	    result = Math.max(result, cnt);

	    for(int i = 0; i < 4; i++) {
	        int ny = y + dy[i];
	        int nx = x + dx[i];
        	if(isIn(ny, nx) && !visited[ny][nx] && !checkAlpha[map[ny][nx] - 'A']) {
                visited[ny][nx] = true;
                checkAlpha[map[ny][nx] - 'A'] = true;
                
                // 다음 깊이 탐색
                dfs(ny, nx, cnt+1);
                // 원복
                checkAlpha[map[ny][nx] - 'A'] = false;
                visited[ny][nx] = false;
            }
	    }
	}
	
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < R && 0 <= x && x < C;
	}
	
}
