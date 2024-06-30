import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, -1, 0, 1};
    static final int[] dx = {1, 0, -1, 0};
    static int N, M; // N :가로
    static int[][][] arr;
    static int[] a1 = new int[2];
    static int[] a2 = new int[2];
    static int[] b1 = new int[2];
    static int[] b2 = new int[2];
    static boolean[][] isVisited;
    static boolean flag = false; // 경로 갈 수 없는지 확인!
    static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // array 초기화
        arr = new int[M+1][N+1][2];
        
        
        st = new StringTokenizer(br.readLine());
        // 세로, 가로로 저장
        a1[1] = Integer.parseInt(st.nextToken());
        a1[0] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        // 세로, 가로로 저장
        a2[1] = Integer.parseInt(st.nextToken());
        a2[0] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        // 세로, 가로로 저장
        b1[1] = Integer.parseInt(st.nextToken()); // 가로
        b1[0] = Integer.parseInt(st.nextToken()); // 세로
        
        st = new StringTokenizer(br.readLine());
        // 세로, 가로로 저장
        b2[1] = Integer.parseInt(st.nextToken());
        b2[0] = Integer.parseInt(st.nextToken());
	   
        // 첫번째 BFS
        isVisited = new boolean[M+1][N+1];
        // 우선 불가능한 경우의 수 또한 append한다.
        isVisited[b1[0]][b1[1]] = true;
        isVisited[b2[0]][b2[1]] = true;
        List<int[]> trackList = bfsTrack(a1[0], a1[1], a2[0], a2[1]);
        
        // 두번째 BFS
        isVisited = new boolean[M+1][N+1];
        isVisited[a1[0]][a1[1]] = true;
        isVisited[a2[0]][a2[1]] = true;
        int r1 = 0; // a1 최단거리 count
        for(int[] tmp: trackList) {
            r1++;
            isVisited[tmp[0]][tmp[1]] = true; // 가지 못하는 경우 visited처리 해주기
        }
        
        // 그리고 두 번째 bfs를 돌려준다.
        int r2 = bfs(b1[0], b1[1], b2[0], b2[1]);
        if(r2 > -1) {
            flag = true;
            result = Math.min(result, r1 + r2);
        }
        
        
        // 세번째 BFS
        isVisited = new boolean[M+1][N+1];
        // 우선 불가능한 경우의 수 또한 append한다.
        isVisited[a1[0]][a1[1]] = true;
        isVisited[a2[0]][a2[1]] = true;
        trackList = bfsTrack(b1[0], b1[1], b2[0], b2[1]);
        
        // 네번째 BFS
        isVisited = new boolean[M+1][N+1];
        isVisited[b1[0]][b1[1]] = true;
        isVisited[b2[0]][b2[1]] = true;
        
        r1 = 0; // b1 최단거리 count
        for(int[] tmp: trackList) {
            r1++;
            isVisited[tmp[0]][tmp[1]] = true; // 가지 못하는 경우 visited처리 해주기
        }
        
        for(int i = 0; i< M+1; i++) {
            for(int j = 0; j < N+1; j++) {
                arr[i][j] = new int[]{0, 0};
            }
        }
        
        r2 = bfs(a1[0], a1[1], a2[0], a2[1]);
        if(r2 > -1) {
            flag = true;
            result = Math.min(result, r1 + r2);
        }
        
        
        // 정답출력
        if(flag) {
            System.out.println(result);
        } else {
            System.out.println("IMPOSSIBLE");
        }
	}
	
	
	static boolean isValid(int y, int x) {
	    return 0 <= y && y < M+1 && 0 <= x && x < N+1;
	}
	
	static int bfs(int sY, int sX, int eY, int eX) {
    // 먼저 BFS를 통해 돌려보자
	   Queue<int[]> q = new ArrayDeque<>();
	   
	   q.add(new int[]{sY, sX});
	   isVisited[sY][sX] = true;
	   // 거리 count
	   while(!q.isEmpty()) {
	       int[] now = q.poll();
	       int y = now[0];
	       int x = now[1];
	       
	       // 종료조건
	       if(y == eY && x == eX) return arr[y][x][0];
	       
	       for(int i = 0; i < 4; i++) {
	           int ny = y + dy[i];
	           int nx = x + dx[i];
	           
	           // 갈수 없으면 무시한다.
	           if(!isValid(ny, nx)) continue;
	           if(isVisited[ny][nx]) continue;
	           isVisited[ny][nx] = true; // 방문처리
	           arr[ny][nx] = new int[] {arr[y][x][0] + 1};
               q.offer(new int[] {ny, nx});
	       }
	       
	   }
	   // 갈 수 없는 경우!
	   return -1;
	}
	
    static List<int[]> bfsTrack(int sY, int sX, int eY, int eX) {
       // 먼저 BFS를 통해 돌려보자
       Queue<int[]> q = new ArrayDeque<>();
       
       q.add(new int[]{sY, sX});
       isVisited[sY][sX] = true;
       
       
       while(!q.isEmpty()) {
           int[] now = q.poll();
           int y = now[0];
           int x = now[1];
           
           if(y == eY && x == eX) {
               List<int[]> trackList = new ArrayList<>();
               int tY = y;
               int tX = x;
    
               while(true) {
                   if (tY == sY && tX == sX) {
                       break;
                   }
                       // 다음 위치를 임시 변수에 저장
                    int nextY = arr[tY][tX][0];
                    int nextX = arr[tY][tX][1];
                
                    // tY와 tX를 한꺼번에 갱신
                    tY = nextY;
                    tX = nextX;
                    
    
                   trackList.add(new int[]{tY, tX});
               }
               
               return trackList;
           }
           
           
           for(int i = 0; i < 4; i++) {
               int ny = y + dy[i];
               int nx = x + dx[i];
               
               // 갈수 없으면 무시한다.
               if(!isValid(ny, nx)) continue;
               if(isVisited[ny][nx]) continue;
               isVisited[ny][nx] = true; // 방문처리
               arr[ny][nx] = new int[] {y, x}; // 이전 좌표값 저장
              q.offer(new int[] {ny, nx});
               
           }
           
       }
       return null;
	}
}