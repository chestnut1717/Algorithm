import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, -1, 0, 1};
    static final int[] dx = {1, 0, -1, 0}; // 동 북 서 남
    static int N, M, K; // 세로, 가로, 이동횟수
    static int[][] map;
    static int direction = 0; // 기본은 동쪽
    static int dice[][] = {
        {0, 2, 0, 0},
        {4, 1, 3, 6},
        {0, 5, 0, 0},
        {0, 6, 0, 0}
    };
    static int result; // 정답
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// map입력
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());
		    }
		}

	    
	    int y = 0;
	    int x = 0;
	    int r = 0;
	    int c = 0;
	    for(int i = 0; i < K; i++) {
	        // 주사위 굴리기

    	    
    	    
	        // 이동 방향대로 한 칸 이동한다.
	        int ny = y + dy[direction];
	        int nx = x + dx[direction];
	        
	        // 만약 그 방향이 없으면
	        if(!isValid(ny, nx)) {
	            direction = (direction + 2) % 4;
	            ny = y + dy[direction];
	            nx = x + dx[direction];
	            
	        }
	        int ry = r + dy[direction];
	        int cx = c  +dx[direction];
	        
	        r = ry;
	        c = cx;
	        rollDice(direction);
	        // 위치 할당
	        y = ny;
	        x = nx;
	        
	        
            // 이동한 후 => 도착한 칸 (x, y) 점수 획득 B
	        // 이때 bfs를 활용한다.
	        int cnt = bfs(y, x);
	        result += cnt;
	        


    	    // A와 B 비교
    	    // 1. 만약 A > B => 이동방향 90도 시계방향
    	    // 2. 만약 A < B => 이동방향 90도 반시계
    	    // 3. 만약 A == B 무시
    	    int val = map[ny][nx]; // A
    	    int diceBtm = dice[1][3]; // B
    	    if(val < diceBtm) {
    	        direction = (direction + 3) % 4; 
    	    } else if (val > diceBtm) {
    	        direction = (direction + 1) % 4;
    	    }

	    }
	    System.out.println(result);

		
	}
	static boolean isValid(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static int bfs(int y, int x) {
	    boolean visited[][] = new boolean[N][M];
	    int val = map[y][x];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        
	    // 개수 cnt
	    int cnt = map[y][x]; // 자기자신 포함
	    // BFS로 동서남북으로 갈 수 있는 모든 경우 탐색
	    while(!q.isEmpty()) {
	        int[] coor = q.poll();
	        int nowY = coor[0];
	        int nowX = coor[1];
	        
	        for(int i = 0; i < 4; i++) {
	            int ny = nowY + dy[i];
	            int nx = nowX + dx[i];
	            
	            // 탐색을 한다.
	            if(!isValid(ny, nx)) continue;
	            if(visited[ny][nx]) continue;
	            visited[ny][nx] = true;
	            if(val == map[ny][nx]) {
	                q.offer(new int[] {ny, nx});
	                cnt+= val;
	            }
	        }
	        
	    }
	    
	    return cnt;
	}
	
	
	static void rollDice(int dir) {
	    switch(dir) {
	        // 동쪽으로 굴러가기
	        case 0:
	            int tmp = dice[1][3];
	            for(int j = 3; j > 0; j--) {
	                dice[1][j] = dice[1][j-1];
	            }
	            dice[1][0] = tmp;
	            dice[3][1] = dice[1][3];
	            break;
	       // 북쪽
	       case 1:
	            tmp = dice[0][1];
	            for(int j = 0; j < 3; j++) {
	                dice[j][1] = dice[j+1][1];
	            }
	            dice[3][1] = tmp;
	            dice[1][3] = dice[3][1];
	            break;
	       // 서쪽
	       case 2:
	            tmp = dice[1][0];
	            for(int j = 0; j < 3; j++) {
	                dice[1][j] = dice[1][j+1];
	            }
	            dice[1][3] = tmp;
	            dice[3][1] = dice[1][3];
	            break;
	       case 3:
	            tmp = dice[3][1];
	            for(int j = 3; j > 0; j--) {
	                dice[j][1] = dice[j-1][1];
	            }
	            dice[0][1] = tmp;
	            dice[1][3] = dice[3][1];
	            break;
	            
	    }
	}
}