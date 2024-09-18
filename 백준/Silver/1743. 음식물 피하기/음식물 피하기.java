import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, -1, 0, 1};
    static final int[] dx = {1, 0, -1, 0};
    static int[][] arr;
    static boolean[][] visited;
    static int N, M, K;
    static int result = Integer.MIN_VALUE;
    static List<int[]> coorList = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1][M+1];
		arr = new int[N+1][M+1];
		
		for(int i = 0; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int y = Integer.parseInt(st.nextToken());
		    int x = Integer.parseInt(st.nextToken());
		    
		    arr[y][x] = 1;
		    coorList.add(new int[] {y, x});
		}
		
		for(int[] coor: coorList) {
		    int y = coor[0];
		    int x = coor[1];
		    if(visited[y][x]) continue; // 이미 방문했을 경우
		    result = Math.max(bfs(coor), result);
		}
		
		
		System.out.println(result);
		
	}
	static int bfs(int[] coor) {
	    Queue<int[]> q = new ArrayDeque<>();
	    int cnt = 1;
	    int y = coor[0];
	    int x = coor[1];
	    visited[y][x] = true;
	    
	    q.offer(coor);
	    
	    while(!q.isEmpty()) {
	        int[] newCoor = q.poll();
	        y = newCoor[0];
	        x = newCoor[1];
	        for(int i = 0; i < 4; i++) {
	            int ny = y + dy[i];
	            int nx = x + dx[i];
	            
	            
	            if(!isValid(ny, nx)) continue;
	            if(arr[ny][nx] == 0) continue;
	            if(visited[ny][nx]) continue; // 방문했으면
	            visited[ny][nx] = true;
	            cnt++;
	            q.offer(new int[] {ny, nx});

	            
	            
	        }
	    }
	    
	    return cnt;
	    
	}
	static boolean isValid(int y, int x) {
	    return 1 <= y && y <= N && 1 <= x && x <= M; 
	}
}