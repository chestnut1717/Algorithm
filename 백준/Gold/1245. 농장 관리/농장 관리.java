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


		        // 산봉우리 후보 넣어두는 리스트
		        List<Pair> list = new ArrayList<>();
		        // 각각 range
		        int height = arr[i][j];
		        
		        // 산봉우리 후보 탐색
		        Queue<Pair> q = new ArrayDeque<>();
		        // 우선 넣어주고
		        q.offer(new Pair(i, j));
		        list.add(new Pair(i, j));
		        // 방문처리
		        visited[i][j] = true;
		        
		       	while(!q.isEmpty()) {
		            Pair coor = q.poll();
		            int y = coor.y; int x = coor.x;
		            
		            // 8방탐색
		            for(int a = 0; a < 8; a++) {
		                int ny = y + dy[a];
		                int nx = x + dx[a];
		                // 그리드를 벗어나지 않고, 방문하지 않은거면서 이전 값과 다음 값이 동일한지
		                if(isValid(ny, nx) && !visited[ny][nx]) {
		                    visited[ny][nx] = true;
		                    if(arr[y][x] == arr[ny][nx]) {
    		                    q.offer(new Pair(ny, nx));
    		                    list.add(new Pair(ny, nx));
    		                }
    		                else if(arr[y][x] < arr[ny][nx]) {
    		                    visited[ny][nx] = false;
    		                }
		                }

		            }
		        }

		        // 그리고 산봉우리 후보군이 진짜 산봉우리인지 탐색하자.
		        if(list.size() > 0) {
                    if(isSan(list)) result++;
		        }
		    }
		}
		
		System.out.println(result);
	}
	static boolean isSan(List<Pair> list) {
	    boolean sanVisited[][] = new boolean[N][M];
        Queue<Pair> q = new ArrayDeque<>();
        for(Pair p: list) {
            q.offer(p);
            sanVisited[p.y][p.x] = true;
        }
        
        int height = arr[q.peek().y][q.peek().x];
        while(!q.isEmpty()) {
            Pair coor = q.poll();
            int y = coor.y;
            int x = coor.x;
            
            for(int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(isValid(ny, nx) && !sanVisited[ny][nx]) {
                    sanVisited[ny][nx] = true;
                    // 혹시라도 주위의 값 중 하나라도 같거나 크다면 => 나가리
                    if(height <= arr[ny][nx]) return false;
                }
            }
        }
        return true;
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
	    
	    public String toString(){
	        return String.format("(y: %d, x: %d)\n", this.y, this.x);
	    }
	}
}