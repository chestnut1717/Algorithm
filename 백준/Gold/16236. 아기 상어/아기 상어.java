import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, -1, 0, 1};
    static int N; // 행, 열 크기
    static int[][] map; // 물고기들 정보 담기는 크기
    static Pair babyShark; // 아기 상어 현재 위치
    static Queue<Pair> pq;
    static int currentSize = 2; // 아기 상어 현재 크기
    static int cnt = 0; // 상어가 먹은 물고기
    static int result = 0; // 엄마 상어 도움 없이 몇 초까지??
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		// 물고기 정보 입력
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());
		        if(map[i][j] == 9) babyShark = new Pair(i, j, 0); // 숫자가 9라면, 아기상어 위치 저장
		    }
		}
		
		// 1. 매 순간마다 아기상어는 BFS를 통해 자신이 먹을 수 있는 물고기를 탐색해야 함
		BFS();
		
		System.out.println(result);
		
		
		
	}
	// 상어가 목적지까지 가는 시간 계산
	static int calcTime(Pair fish) {
	    return Math.abs(fish.x - babyShark.x) + Math.abs(fish.y - babyShark.y); 
	}
	
	// 먹이 탐지 bfs
	static void BFS() {
	    // pq가 빌 때까지 반복함
	    while (true) {
	        
    	    Queue<Pair> q = new ArrayDeque<>(); // 위치 탐색 큐
    	    pq = new PriorityQueue<>(); // pq 초기화
    	    boolean[][] visited = new boolean[N][N];// 방문 탐색 여부
    	    boolean flag = true; // while문을 계속 반복할 것인지 여부 체크
    	    int limit = 0; // 어느 depth까지 탐색 가능하게 할 것인지
    	    
    	    visited[babyShark.y][babyShark.x] = true;
    	    q.offer(babyShark);// 우선 아기상어 좌표 넣기
    	    // 우선 큐를 통해 사방 탐색 진행
    	    while(!q.isEmpty()) {
    	        Pair current = q.poll();
    	        
    	        int y = current.y;
    	        int x = current.x;
    	        int depth = current.n;
    	        
    	        // 만약 이미 가능한 물고기가 잡혔으면서, 다음 depth로 넘어왔을 때
    	        if(flag == false && depth > limit) {
    	            break;
    	        }
    	        
    	        // 사방 탐색 진행
    	        for(int i = 0; i < 4; i++) {
    	            int ny = y + dy[i];
    	            int nx = x + dx[i];
    	           // System.out.printf("ny: %d, nx: %d%n", ny, nx);
    	            // 움직일 수 있는 최소 조건
    	            if(isValid(ny, nx) && !visited[ny][nx] && isMoreBig(ny, nx)) {
    	               // System.out.printf("ny: %d, nx: %d%n", ny, nx);
    	                visited[ny][nx] = true;
    	                // 먹을 수 있다면
    	                if(map[ny][nx] >= 1 && map[ny][nx] < currentSize) {
    	                    pq.add(new Pair(ny, nx, depth+1));
    	                    flag = false;
	                        limit = depth;
    	                }
    	                // 먹을 수는 없다면 
    	                else {
    	                    q.offer(new Pair(ny, nx, depth+1));
    	                }
    	            }
    	            
    	        }
    	        
    	       // // 해당 depth에서 하나라도 발견했으면, 종료
    	       // if (pq.size() > 0) break;
    	       
    	    }
	        // 더 이상 탐색할 수 없을시에는 종료
	        if(pq.size() == 0) return;
	       // System.out.println(pq);
	        // 우선순위큐에서 가장 만만한 것을 우선 먹는다.
	        Pair next = pq.poll();
	        result += next.n; // 걸리는 시간 더해주기
	        cnt++;
	        map[next.y][next.x] = 0;
		    map[babyShark.y][babyShark.x] = 0;
		    babyShark.y = next.y;
		    babyShark.x = next.x;
		    // 마지막 아기 상어 위치를 map에 찍음
		    map[babyShark.y][babyShark.x] = 9;
	        // 다음 물고기 변신 조건
	        if(cnt == currentSize) {
	            cnt = 0;
	            currentSize++;
	        }

	    }

	    
	}
	
	static boolean isValid(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < N;
	}
	// 통과 여부를 체크할 수 있는 함수(상어의 크기는 물고기의 크기보다 같거나 커야 함)
	static boolean isMoreBig(int y, int x) {
	    return map[y][x] <= currentSize;
	}
	
	// [디버깅 코드(맵 출력)]
	static void printMap() {
	    for(int y = 0; y < N; y++) {
	        for(int x = 0; x < N; x++) {
	            System.out.printf("%d ", map[y][x]);
	        }
	        System.out.println();
	    }
	    System.out.println();
	}
	
	// 좌표 담는 클래스
static class Pair implements Comparable<Pair>{
        int y, x, n;
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
        public Pair(int y, int x, int n) {
            this.y = y;
            this.x = x;
            this.n = n;
        }
        
        @Override
        public String toString() {
            return "[y : " + y + " x: " + x + " n: " + n + "]";
        }
        
        @Override
        public int compareTo(Pair o) {
            if (this.y == o.y) {
                // 가장 왼쪽에 있는 물고기부터 처리(x가 작은 것부터 처리
                return Integer.compare(this.x, o.x);
            }
            return Integer.compare(this.y, o.y);
        }
    }
}
