import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
        
        visited = new boolean[N][N];
        // 이제 각각의 grid를 탐색을 해서
        // 해당 grid가 방문했는지 먼저 알아본 후 => 방문했으면 무시
        // bfs를 통해서 connected component를 찾는다.
        // connected component를 찾는 방법은 1이 있는지 탐색하면 된다.
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // System.out.println(map[i][j]);
                if(!visited[i][j] && map[i][j] == 1) {
                    visited[i][j] = true;
                    bfs(i, j);
                }
                
            }
        }
        
        sb.append(pq.size()).append('\n');
        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
        

        
        
	}
	static void bfs(int y, int x) {
	    Queue<int[]> q = new ArrayDeque<>();
	    q.offer(new int[] {y, x});
	    int cnt = 1;
	    
	    while(!q.isEmpty()) {
	        int[] coor = q.poll();
	        for(int i = 0; i < 4; i++) {
	            int ny = coor[0] + dy[i];
	            int nx = coor[1] + dx[i];
	            if( 0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx] && map[ny][nx] == 1) {
	                visited[ny][nx] = true;
	                cnt++;
	                q.offer(new int[] {ny, nx});
	            }
	        }
	    }
        pq.offer(cnt);
	    return;
	}
	
}