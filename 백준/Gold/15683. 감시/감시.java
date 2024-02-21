import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * @see <a href="https://www.acmicpc.net/problem/1620">
 * - 문제 : BJ 15683 감시
 * - 난이도 : 중
 * - 아이디어 : 전형적인 dfs + 구현 문제
 * - cctv의 회전을 고려하면서, cctv가 탐지하지 못하는 경우의 수(ex. 벽), 탐지하는 경우의; 수를 모두 고려
 * - 중복순열을 활용하여 n개의 cctv의 모든 경우의 수를 구한 후 반복문 돌림
 * - 시간복잡도 : N^8(중복순열 개수) * 4(방향 rotate) but cctv는 최대 8개 시간제한 있으므로 충분히 가능 
 */
public class Main {
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int N, M;
    static int[][] arr;
    static int[][] track; // 현재 cctv의 사각지대를 찾아보는 경우
    static int cnt; // cctv 개수
    static int[] possible; // 회전 경우의 수 담는 배열
    static List<List<Integer>> possibleLst = new ArrayList<>(); // 가능한 모든 경우의 
    static List<Pair> cctv = new ArrayList<>(); // CCTV 좌표 모두 담는 경우의 수
    static int result = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 현재 사무실 상태 입력받기
		arr = new int[N][M];
	    track = new int[N][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		        if(1 <= arr[i][j] && arr[i][j] <= 5) {
		            cctv.add(new Pair(i, j)); // cctv만 따로 저장
		            cnt++;
		        }
		    }
		}
		
		// 중복조합을 위한 변수 초기화
		possible = new int[cnt];
		// 우선 track 배열을 array변수 그대로 복사함
		copy();
		// 중복순열 구하기
		perm(0);
		
		// 모든 회전하는 경우의 수에 대해서 구하기
		for(List<Integer> lst: possibleLst) {
		    // 모든 direction에 대해 구하기
		    for(int i = 0; i < lst.size(); i++) {
		        Pair nowCCTV = cctv.get(i); // 현재 cctv
		        int direction = lst.get(i); // direction
		        rotate(nowCCTV, direction); // 돌리고 돌리고

 		    }
 		    
 		    // 사각지대 최소의 개수 갱신
 		    result = Math.min(count(), result);
 		    // 다음 iteration을 위해 사각지대 개수 초기화
 		    copy();
 		    
		}
		
		
		System.out.println(result);
        

	}
	// 0 개수 count하는 메소드
	static int count() {
	    int zeroCnt = 0;
        for(int j = 0; j < N; j++) {
            for(int k = 0; k < M; k++) {
                if(track[j][k] == 0) zeroCnt++;
            }
        }
        return zeroCnt;
	}
    // 변수 count
	static void copy() {
	    for(int j = 0; j < N; j++) {
            for(int k = 0; k < M; k++) {
        		track[j][k] = arr[j][k];
        	}
        }
	}
	// 범위 밖의 좌표 탐지
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < M;
	}
	// 특정 cctv를 회전시키고, cctv 탐지구역 칠해주는 함수
	static void rotate(Pair nowCCTV, int direction) {
	    int y = nowCCTV.y;
	    int x = nowCCTV.x;
	    
	    if (arr[y][x] == 1) {
            dfs(y, x, direction % 4);
	    }
	    else if (arr[y][x] == 2) {
	        dfs(y, x, direction % 4);
	        dfs(y, x, (direction + 2) % 4);
	    }
	    else if (arr[y][x] == 3) {
	        dfs(y, x, direction % 4);
	        dfs(y, x, (direction+3) % 4);
	    }
	    else if (arr[y][x] == 4) {
	        dfs(y, x, (direction) % 4);
	        dfs(y, x, (direction+2) % 4);
	        dfs(y, x, (direction+3) % 4);
	    }
	    else if (arr[y][x] == 5) {
	        dfs(y, x, (direction) % 4);
	        dfs(y, x, (direction+1) % 4);
	        dfs(y, x, (direction+2) % 4);
	        dfs(y, x, (direction+3) % 4);
	    }
	    
	}
	
	// cctv 탐지 가능 경우를 깊이우선 탐색
	static void dfs(int y, int x, int direction) {
	    if(!isIn(y, x) || arr[y][x] == 6) {
	        return; // 종료 조건
	    }
	    track[y][x] = 7; // #표시
	    int ny = y + dy[direction];
	    int nx = x + dx[direction];
	    dfs(ny, nx, direction);
	}
	
	// n개의 cctv의 모든 방향을 계산하는 중복순열
	static void perm(int depth) {
	    // 종료조건
	    if(depth == cnt) {
	       List<Integer> tmp = new ArrayList<>();
	       for(int i = 0; i < possible.length; i++) {
	           tmp.add(possible[i]);
	       }
	       possibleLst.add(tmp);
	       return;
	    }
	    for(int i = 1; i <= 4; i++) {
	        possible[depth] = i;
	        perm(depth+1);
	    }
	    
	}
	
    // cctv 좌표값 저장하는 클래스 
	static class Pair {
    	    int y;
    	    int x;
    	    public Pair(int y, int x) {
    	        this.y = y;
    	        this.x = x;
    	    }
	}
}
