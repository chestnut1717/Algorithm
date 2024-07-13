import java.util.*;
import java.io.*;

public class Main
{
    static final int[] kdy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] kdx = {2, 1, -1, -2, -2, -1, 1, 2};
    static final int[] qdy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] qdx = {1, 1, 0, -1, -1, -1, 0, 1};
    static int N, M;
    static int[][] board;
    static List<int[]> malList = new ArrayList<>();
    static boolean[][] visited;
 	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    board = new int[N+1][M+1];
	    visited = new boolean[N+1][M+1];
	    
	    // 우선 각각의 좌표 입력
	    // queen = 1, knight = 2 pawn = 3;
	    // queen
	    st = new StringTokenizer(br.readLine());
	    int qCnt = Integer.parseInt(st.nextToken());
	    for(int i = 0; i < qCnt; i++) {
	        int y = Integer.parseInt(st.nextToken());
	        int x = Integer.parseInt(st.nextToken());
	        // q박아놓기
	        board[y][x] = 1;
	        malList.add(new int[] {y, x});
	    }
	    
	    // knight
	    st = new StringTokenizer(br.readLine());
	    int kCnt = Integer.parseInt(st.nextToken());
	    for(int i = 0; i < kCnt; i++) {
	        int y = Integer.parseInt(st.nextToken());
	        int x = Integer.parseInt(st.nextToken());
	        // q박아놓기
	        board[y][x] = 2;
	        malList.add(new int[] {y, x});
	    }
        
        // pawn
        st = new StringTokenizer(br.readLine());
	    int pCnt = Integer.parseInt(st.nextToken());
	    for(int i = 0; i < pCnt; i++) {
	        int y = Integer.parseInt(st.nextToken());
	        int x = Integer.parseInt(st.nextToken());
	        // q박아놓기
	        board[y][x] = 3;
	        malList.add(new int[] {y, x});
	    }
	    
	    // 다 입력했으면 이제 탐색을 진행한다.
	    // 우선 말은 장애물의 영향을 받지 않는다.
	    // 반대로 퀸은 장애물의 영향을 받는다.
	    // 폰은 움직이지 않음 = 단순히 장애물
	    for(int[] mal : malList) {
	        int y = mal[0];
	        int x = mal[1];
	        switch(board[y][x]) {
	            // queen
	            case 1:
	                // 우선 방문처리
	                visited[y][x] = true;
	                // 8방향으로 탐색해서 갈 수 있는데까지 탐색
	                for(int i = 0; i < 8; i++) {
	                    int qy = y;
	                    int qx = x;
	                    while(true) {
	                        int ny = qy + qdy[i];
	                        int nx = qx + qdx[i];
	                        // 범위에 없다면 끝내기
	                        if(!(0 < ny && ny <= N && 0 < nx && nx <= M)) break;
	                        // 만약 다음 칸이 어딘가 막혀있다면 끝내기
	                        if(board[ny][nx] != 0) break;
	                        else {
	                           visited[ny][nx] = true;
	                           qy = ny;
	                           qx = nx;
	                        }
	                    }
	                }
	                break;
	            // knight
	            case 2:
	                visited[y][x] = true;
	                for(int i = 0; i < 8; i++) {
	                    int ny = y + kdy[i];
	                    int nx = x + kdx[i];
	                    // 유효한 곳이고 방문 안했다면
	                    if(0 < ny && ny <= N && 0 < nx && nx <= M && !visited[ny][nx]) {
	                        visited[ny][nx] = true;
	                    }
	                }
	                break;
	            // pawn
	            case 3:
	                visited[y][x] = true;
	                break;
	            default:
	                break;
	           
	        }
	    }
	    
	    // 이제 visited되지 않은것만 센다.
	    int result = 0;
	    for(int y = 1; y < N+1; y++) {
	        for(int x = 1; x < M+1; x++) {
	            if(visited[y][x] == false) result++;
	        }
	    }
	    System.out.println(result);
	}
}