import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] waterTime;
    static List<Pair> lst = new ArrayList<>(); // 물을 담을 것!
    static boolean[][] visitedA;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int startY, startX;
    static int endY, endX;
    static boolean flag = false;
    static int result = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// map 입력
		map = new char[R][C];
		waterTime = new int[R][C];

		
	    for(int i = 0; i < R; i++) {
	        char[] tmpCharArr = br.readLine().toCharArray();
	        for(int j = 0; j < C; j++) {
	            map[i][j] = tmpCharArr[j];
	            if(map[i][j] == 'S') {
	                startY = i;
	                startX = j;
	            } else if(map[i][j] == 'D') {
	                endY = i;
	                endX = j;
	            } else if(map[i][j] == '*') {
	                lst.add(new Pair(i, j));
	            }
	        }
	    }
	    
	    water();


	    // 물이 끝났으면 고슴도치를 한 칸씩 이동한다.
	    move(startY, startX);

	    if(flag == false) System.out.println("KAKTUS");
	    else System.out.println(result);
	    
	}
	
	static void move(int sy, int sx) {
	   Queue<Pair> q = new ArrayDeque<>();
	   int[][] visited = new int[R][C];
	   
	   q.offer(new Pair(sy, sx));
	   // 우선 다 채워준다.
       int cnt = 0;
	   while(!q.isEmpty()) {
	       Pair tmpPair = q.poll();
	       int y = tmpPair.y;
	       int x = tmpPair.x;
	       
	       for(int i = 0; i < 4; i++) {
	            int ny = y + dy[i];
	            int nx = x + dx[i];
	            // 종료조건

	            
	            if(!isIn(ny, nx)) continue;
	            if(visited[ny][nx] > 0) continue;
	            if(canAnimalGo(ny, nx, visited[y][x])) {
	                visited[ny][nx] = visited[y][x] + 1;
	                if(ny == endY && nx == endX) {
    	                flag = true;
    	                result = Math.min(visited[ny][nx], result);
    	                
    	                return;
    	            }
	                q.offer(new Pair(ny, nx));
	            }
	        }
	    }
	}
	
	// 물이 차오르는 함수
	static void water() {
	   Queue<Pair> q = new ArrayDeque<>();
	   boolean[][] visited = new boolean[R][C];
	   
	   
	   // 우선 다 채워준다.
	   for(Pair p: lst) {
	       q.offer(new Pair(p.y, p.x));
	       visited[p.y][p.x] = true;
	       waterTime[p.y][p.x] = 0;
	   }
        
       int cnt = 0;
	   while(!q.isEmpty()) {
	       Pair tmpPair = q.poll();
	       int y = tmpPair.y;
	       int x = tmpPair.x;
	        for(int i = 0; i < 4; i++) {
	            int ny = y + dy[i];
	            int nx = x + dx[i];
	            if(isIn(ny, nx) && canGo(ny, nx) && !visited[ny][nx]) {
	                visited[ny][nx] = true;
	                waterTime[ny][nx] = waterTime[y][x] + 1;
	                q.offer(new Pair(ny, nx));
	                map[ny][nx] = '*';
	            }
	        }
	    }
	}
	
	// 고슴도치가 갈 수 있는 경우
	
	static boolean canAnimalGo(int y, int x, int cnt) {
	     
	    if (map[y][x] == 'X') return false; // 돌일 때
	    if (map[y][x] == '*' && waterTime[y][x] <= cnt + 1) return false;
	    return true;
	}
	
	// 물이 퍼질 수 있는 경우
	static boolean canGo(int y, int x) {
	    if (map[y][x] == 'X') return false; // 돌일 때
	    if (map[y][x] == 'D') return false; // 굴일 때
	    return true;
	}
	
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < R && 0 <= x && x < C;
	}
	
	static class Pair {
	    int y, x;
	    public Pair(int y, int x) {
	        this.y = y;
	        this.x = x;
	    }
	}
}