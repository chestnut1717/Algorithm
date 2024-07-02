import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    static int N, M;
    static int[][] arr;
    static boolean[][] visited; // 산봉우리 후보 탐색하는 배열
    static int result; // 산봉우리 개수
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 격자 입력
		arr = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// 우선 BFS를 통해 산봉우리 후보를 탐색한다.
		for(int i = 0; i < N; i++) {
		    for(int j = 0; j < M; j++) {
		        if (visited[i][j]) continue;
                
                // 산봉우리인지 여부
                boolean isSan = true;

		        // 산봉우리 후보 넣어두는 리스트
		        List<Pair> list = new ArrayList<>();
		        // 각각 range
		        int height = arr[i][j];
		        
		        // 산봉우리 후보 탐색
		        Queue<Pair> q = new ArrayDeque<>();
		        // 우선 넣어주고
		        q.offer(new Pair(i, j));
		        visited[i][j] = true;
		        
		       	while(!q.isEmpty()) {
		            Pair coor = q.poll();
		            int y = coor.y; int x = coor.x;
		            
		            // 8방탐색
		            for(int a = 0; a < 8; a++) {
		                int ny = y + dy[a];
		                int nx = x + dx[a];
		                // 그리드를 벗어나지 않고
		                if(isValid(ny, nx)) {
		                    // 방문하지 않았고 봉우리가 확장가능한 경우
		                    if(!visited[ny][nx] && arr[ny][nx] == height) {
		                        visited[ny][nx] = true;
		                        q.offer(new Pair(ny, nx));
		                    }
		                    
		                    // 방문 여부와 상관없이 봉우리 탐색 가능
		                    if(arr[ny][nx] > height) {
		                        isSan = false;
		                    }
		                }

		            }
		        }
		        
		        if(isSan) result++;


		    }
		}
		
		System.out.println(result);
	}

	static boolean isValid(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static class Pair {
	    int y, x;
	    public Pair(int y, int x) {
	        this.y = y;
	        this.x = x;
	    }
	}
}