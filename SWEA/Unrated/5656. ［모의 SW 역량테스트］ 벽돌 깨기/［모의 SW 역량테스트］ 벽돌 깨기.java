import java.io.*;
import java.util.*;

public class Solution{
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int T;
    static int N, W, H;
    static int[][] map;
    static int[][] copyMap;
    static List<List<Integer>> permList;
    static int result;
    static int[] numbers;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine()); // T
		
		for(int test_case = 1; test_case <= T; test_case++) {
		    
		    // 입력 받음
		    st = new StringTokenizer(br.readLine());
		    N = Integer.parseInt(st.nextToken());
		    W = Integer.parseInt(st.nextToken());
		    H = Integer.parseInt(st.nextToken());
		    
		    // map채우기
		    map = new int[H][W];
		    
		    for(int i = 0; i < H; i++) {
		        st = new StringTokenizer(br.readLine());
		        for(int j = 0; j < W; j++) {
		            map[i][j] = Integer.parseInt(st.nextToken());

		        }
		    }
		    
		    // 우선 구슬 움직이는 모든 경우의 수를 구한다.(N가지니깐 중복순열10PN)
		    permList = new ArrayList<>();
		    numbers = new int[N];
		    
		    // 모든 경우의 수 구해줌
		    perm(W, N, 0);
		    
		    
            
            int result = Integer.MAX_VALUE;
            // 그리고 그 경우의 수에 대해 하나씩 구슬을 던져준다.
            for(List<Integer> choice: permList) {
                
                // 임시 최솟값 구하기
                int tmpMin = solve(choice);
                result = Math.min(tmpMin, result);
            }
		    
		    
		    System.out.printf("#%d %d\n", test_case, result);
		}
		
		
	}
	
	// 이제 해당 경우의 수를 가지고 문제를 품
	static int solve(List<Integer> choice) {
	    int tmpMin = 0;
	    
	    copyMap = new int[H][W];
	    // map copy
	    for(int y = 0; y < H; y++) {
	        for(int x = 0; x < W; x++) {
	            copyMap[y][x] = map[y][x];
	        }
	    }
	    
	    // 한 턴마다 구슬을 던져준다!
	    for(int x: choice) {
	        Pair hitLocation = throwMarble(x);
	        // 만약 던진 구간에 아무것도 없다면 무시
	        if(hitLocation.x == -1) continue;
	        // 연쇄 터트림 작업 시작
	        tmpMin += bomb(hitLocation);

	        moveBlock();
	    }
        // 세기
        int cnt = 0;
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(copyMap[i][j] > 0) cnt++;
            }
        }
        

	    return cnt;
	    
	}
	
	// 다 폭발시켰으면, 이동시켜주는 로직만 작성하자
	static void moveBlock() {
	    // 0~W-1 열까지 다 탐색해준다음! 개수 세고, 갱신해준다.
	    
	    
	    // 한 열씩 탐색한다.
	    for(int x = 0; x < W; x++) {
	        Queue<Integer> q = new ArrayDeque<>();
	        for(int y = H-1; y >= 0; y--) {
	            if(copyMap[y][x] == 0) continue;
	            else q.offer(copyMap[y][x]);
	            
	        }
	        
	        // 그리고 다 초기화
	        for(int y = H-1; y >= 0; y--) {
	            copyMap[y][x] = 0;
	        }
	       // System.out.println(q.toString());
	        // 차근차근 더해주기
	        int idx = H-1;
	        while(!q.isEmpty()) {
	            int a = q.poll();
	           // System.out.println(a);
	            copyMap[idx][x] = a;
	            idx--;
	        }

	       // printCopyMap();
	    }
	    
	    
	}
	static void printCopyMap() {
	    for(int i = 0; i < H; i++) {
	        for(int j = 0; j < W; j++) {
	            System.out.printf("%d ", copyMap[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println();
	}
	
	// bfs로 다 터트리는 코드
	static int bomb(Pair hitLocation) {
	    int cnt = 0; // 몇 개 터트렸는지 cnt
	    Queue<Pair> q = new ArrayDeque<>();
	    boolean[][] visited = new boolean[H][W];
	    
	    visited[hitLocation.y][hitLocation.x] = true;
	    q.add(hitLocation);
	    
	    
	    // 큐가 빌때까지 반복
	    while(!q.isEmpty()) {
	        Pair now = q.poll();
	        int y = now.y;
	        int x = now.x;
	        int range = copyMap[y][x] - 1;
	        copyMap[y][x] = 0; // 빈 공간으로 만들기
	        
	        for(int i = 0; i < 4; i++) {
	            int tmpY = y;
	            int tmpX = x;
	            for(int r = 0; r < range; r++) {
	                
	                int ny = tmpY + dy[i];
	                int nx = tmpX + dx[i];
	                
	                if(isIn(ny, nx) && copyMap[ny][nx] != 0 && !visited[ny][nx]) {
	                    visited[ny][x] = true;
	                    q.offer(new Pair(ny, nx));
	                    cnt++;
	                }
	                tmpY = ny;
	                tmpX = nx;
	            }
	        }
	       // printCopyMap();
	        
	    }
	    return cnt;
	}
	
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < H && 0 <= x && x < W;
	}
	
	static Pair throwMarble(int x) {
	    // 0이 아닌 칸이 나올때까지 직진
	    int y = 0;
	    while(y < H) {
	        if(copyMap[y][x] == 0) {
	            y++;
	            continue;
	        } else {
	            return new Pair(y, x);
	        }
	    }
	    // 해당 칸에 아무것도 없으면 종료시킴
	    return new Pair(-1, -1);
	}
	
	
	// 모든 경우의 수 구함!
	static void perm(int W, int N, int cnt) {
	    if(cnt == N) {
	        // 경우의 수가 구해지면 더해준다.
	        List<Integer> tmpList = new ArrayList<>();
	        for(int num: numbers) {
	            tmpList.add(num);
	        }
	        permList.add(tmpList);
	        return;
	    }
	    // 0 ~ W-1
	    for(int i = 0; i < W; i++) {
	        numbers[cnt] = i;
	        perm(W, N, cnt+1);
	        numbers[cnt] = 0;
	    }
	    
	}
	
	static class Pair{
	    int y, x;
	    public Pair(int y, int x) {
	        this.y = y;
	        this.x = x;
	    }
	    
	    @Override
	    public String toString() {
	        return String.format("y:%d, x:%d", this.y, this.x);
	    }
	}
	
}
 