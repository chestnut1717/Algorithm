
import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 1861 정사각형방
 * - 소요시간 : 40분 
 * - 난이도 : 하
 * - 아이디어 : 단순 dfs
 * - 주의할 점 : visited배열을 사용하면 안된다. y, x지점에서 방문한 것을 y+n, x+n지점에서 방문할 가능성이 있다.
 */
class Solution {
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int N;
    static int[][] arr;
    static int saveNum;
    static int result;
    
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력
        
		for(int test_case = 1; test_case <= T; test_case++) {
		
		    N = Integer.parseInt(br.readLine()); // N 입력
		    
		    arr = new int[N][N]; // 배열 초기화
		    result = 0; // result 초기화
		    saveNum = 0; // 방 번호 저장하는 변수
		    
		    // N*N개 입력
		    for(int i = 0; i < N; i++) {
		        st = new StringTokenizer(br.readLine());
		        for(int j = 0; j < N; j++) {
		            arr[i][j] = Integer.parseInt(st.nextToken());
		        }
		    }
		    
		     // 
		    for(int i = 0; i < N; i++) {
		        for(int j = 0; j < N; j++) {

	                int tmpMax = dfs(i, j, 1); // 무조건 1부터 시작
	                if(tmpMax > result) {
	                    result = tmpMax;
	                    saveNum = arr[i][j];
	                }
	                else if (tmpMax == result && saveNum > arr[i][j]) { // 가장 많이 갈 수 있는 경우가 여러 개일때, 최솟값 갱신
	                    saveNum = arr[i][j];
	                }

		        }
		    }
		
		
            System.out.printf("#%d %d %d%n", test_case, saveNum, result);
		}
	}
	
	// dfs순회
    static int dfs(int y, int x, int cnt) {
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(isIn(ny, nx) && arr[ny][nx] == arr[y][x] + 1) { // 만약에 범위 안에 들면서, 일치할 때
                cnt = dfs(ny, nx, cnt+1); // cnt 지속적으로 갱신
            }
        }
        return cnt; // 더 이상 진행하지 못할 때, 받아온 cnt 받아올 수 있음
    }	
    
    // 바운더리 체크
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	
}