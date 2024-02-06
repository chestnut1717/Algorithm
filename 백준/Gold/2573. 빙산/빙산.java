import java.io.*;
import java.util.*;


public class Main {
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int[][] arr;
    static int[][] arrNext;
    static boolean[][] visited;// 방문 체크 확인
    static int N; // 행
    static int M; // 열
    static int cnt; // 빙산 개수 count
    static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M]; // 배열 초기화
		arrNext = new int[N][M];
		visited = new boolean[N][M];
		// 빙산 데이터 입력
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
        
        /// 완료
        // 1. 매 년마다 빙산 줄어드는 것을 확인
        int year = 0;
        while(true) {
            for(int y = 0; y < N; y++) {
                for(int x = 0; x < M; x++) {
                    
                    // 주변 0의 개수를 세어서 빙산 녹이기
                    // 그러나 바로 반영하면 안된다. 1 -> 0이 되는 경우도 많음
                    // arrNext에 넣고 arr에 반영
                    melt(y, x);
                }
            }
            year++; // year 추가
            
            copyArr(); // arr <= arrNext 하고 초기화


            int cnt = countIceberg(); // DFS로 탐색한 후 몇개로 나누어져 있는지 확인
            
            if(isAllMelted()) {
                result = 0;
                break;
            }
            
            if (cnt >= 2) {
                result = year;
                break;
            }

        }
        
        // 결과 출력
        System.out.println(result);
        
	}
	// 빙산의 조각이 얼마나 나누어져있는지 확인
	static int countIceberg(){
	    int count = 0;
	    for(int y = 0; y < N; y++) {
	        for(int x = 0; x < M; x++) {
	            if(arr[y][x] > 0 && !visited[y][x]) {
	                visited[y][x] = true;
	                dfs(y, x);
	                count++;
	            }
	        }
	    }

	    visited = new boolean[N][M];// visited 초기화
	    return count;
	}
	static boolean isAllMelted() {
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < M; j++) {
	            if(arr[i][j] != 0) return false;
	        }
	    }
	    return true;
	}
	// dfs로 탐색하는 경우
	static void dfs(int y, int x){
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(isIn(ny, nx) && !visited[ny][nx] && arr[ny][nx] > 0) {
	            visited[ny][nx] = true;
	            dfs(ny, nx);
            }
        }
	    
	}
	
	// 빙산 주위의 0의 갯수 => 빙산을 녹이는 경우
	static void melt(int y, int x) {
	    
	    int nowVal = arr[y][x]; // 녹기 전 빙산의 높이
	    for(int i = 0; i < 4; i++) { // 주위의 얼음 찾기
	        int ny = y + dy[i];
	        int nx = x + dx[i];
	        
	        if(!isIn(ny, nx)) continue; // 범위 나가면 무시
	        if(arr[ny][nx] == 0 && nowVal > 0) nowVal--; /// 0이면 녹일 필요가 없음
	    }
	    arrNext[y][x] = nowVal;
	}
	
	// 배열의 인덱스 유효성 검사 메소드
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	// arrNext => arr
	static void copyArr() {
	    for(int i = 0; i < N; i++){
	        for(int j = 0; j < M; j++) {
	            arr[i][j] = arrNext[i][j];
	            arrNext[i][j] = 0;
	        }
	    }
	    
	}
	
}
