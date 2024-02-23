import java.util.*;
import java.io.*;

public class Main {
    static final int[] dy = {0, -1, 0, 1};
    static final int[] dx = {1, 0, -1, 0};
    static int N;
    static int M;
    static int arr[][], arrCopy[][];
    static boolean visited[][];
    static int safeZone = Integer.MIN_VALUE;
    static ArrayList<Pair> virus = new ArrayList<>();
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    // 0. 미리 바이러스 부분은 따로 check해준다.
	    arr = new int[N][M];
	    arrCopy = new int[N][M];
	    for(int i = 0; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        for(int j = 0; j < M; j++) {
	            arr[i][j] = Integer.parseInt(st.nextToken());
	            if (arr[i][j] == 2) virus.add(new Pair(i, j));
	        }
	        
	    }
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < M; j++) {
	            arrCopy[i][j] = arr[i][j];
	        }
	    }
	    

	    
	    // 1. 우선 0인 부분에 3개 벽을 놓는다.
	    // 2. 벽을 놓는 각 iteration마다 BFS를 통해 탐색!
	    
	    // 3. 안전거리 최댓값 갱신 => 새로운 ITERATION
	    for(int i = 0; i < N*M; i++) {
	        if (arr[i/M][i%M] == 1 ||  arr[i/M][i%M] == 2) continue;
	        
	        for(int j = i+1; j < N*M; j++){
	            if (arr[j/M][j%M] == 1 ||  arr[j/M][j%M] == 2) continue;
	            
	            for(int k = j+1; k<N*M; k++) {
	                if (arr[k/M][k%M] == 1 ||  arr[k/M][k%M] == 2) continue;
	                arr[i/M][i%M] = 1;
	                arr[j/M][j%M] = 1;
	                arr[k/M][k%M] = 1;
	                BFS();
	                
	                safeZone = Math.max(safeZone, countSafe()); // 안전영역 셈
	                // array 초기화
	                copy();

	            }
	        }
	    }
	    

		
		System.out.println(safeZone);
	}
	static void copy() {
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < M; j++) {
	            arr[i][j] = arrCopy[i][j];
	        }
	    }
	}
	
	static void BFS() {
	    visited = new boolean[N][M]; // visited 초기화
	    Queue<Pair> q = new ArrayDeque<>();
	    for(Pair pair: virus) {
	        q.add(pair);
	    }
	    // q가 빌때까지 탐색
	    while(!q.isEmpty()) {
	        Pair pair = q.poll();
	        
	        for(int i = 0; i < 4; i++) {
	            int ny = pair.y + dy[i];
	            int nx = pair.x + dx[i];
	            if(isIn(ny, nx) && !visited[ny][nx] && arr[ny][nx] == 0) {
	                visited[ny][nx] = true;
	                arr[ny][nx] = 2; // 바이러스는 2번으로 퍼짐
	                q.add(new Pair(ny, nx));
	            }
	        }
	        
	    }
	    
	}
	
	static int countSafe(){
	    int cnt = 0;
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < M; j++) {
	            if(arr[i][j] == 0) cnt++;
	        }

	    }
	    return cnt;
	}
	
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N & 0 <= x && x < M;
	}
	
	static class Pair{
	    int y, x;
	    
	    public Pair(int y, int x){
	        this.y = y;
	        this.x = x;
	    }
	}
	
}
