import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    static int T;
    static int[][] map;
    static boolean[][] visited;
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		// 각 테스트케이스마다 BFS돌리기
		for(int t = 0; t < T; t++) {
	        int N = Integer.parseInt(br.readLine());
	        st = new StringTokenizer(br.readLine());
	        int startY = Integer.parseInt(st.nextToken());
	        int startX = Integer.parseInt(st.nextToken());
	        
	        st = new StringTokenizer(br.readLine());
	        int endY = Integer.parseInt(st.nextToken());
	        int endX = Integer.parseInt(st.nextToken());
	        
	        // 초기화
	        map = new int[N][N];
	        visited = new boolean[N][N];
	        
	        int result = bfs(startY, startX, endY, endX, N);
	        sb.append(result).append('\n');
	        

	        
	        

		}
		
		bw.write(sb.toString());
		bw.close();
	}
	
	static int bfs(int startY, int startX, int endY, int endX, int N) {
	    
	    // 출발점 도착점 동일
	    if(startY == endY && startX == endX) {
	        return 0;
	    }
	    
        Queue<int[]>q = new ArrayDeque<>();
        
        // 우선 start 방문처리
        q.offer(new int[] {startY, startX, 1});
        visited[startY][startX] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            int dist = now[2];
            
            for(int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                

                if(isValid(ny, nx, N) && !visited[ny][nx]) {
                    // 만약 종료조건이라면
                    if(ny == endY && nx == endX) {
                        return dist;
                    }
                    int nDist = dist+1;
                    visited[ny][nx] = true;
                    q.offer(new int[] {ny, nx, nDist});
                }
            }
        }
        
        return -1;
	}
	
	static boolean isValid(int y, int x, int N) {
	    return 0 <= y && y < N && 0 <= x && x < N;
	}
}
