import java.util.*;
import java.io.*;

public class Main {
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int R, C, T; // row, col, time
    static int[][] arr;
    static int[][] arrCopy;
    static List<Integer> cleanArrY;
    static int result;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// row, col, Time 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[R][C];
		cleanArrY = new ArrayList<>();
		
		// 배열 입력받기
		for(int y = 0; y < R; y++) {
		    st = new StringTokenizer(br.readLine());
		    for(int x = 0; x < C; x++) {
		        arr[y][x] = Integer.parseInt(st.nextToken());
		        if(arr[y][x] == -1) cleanArrY.add(y); // x는 항상 0이므로!
		    }
		}
		
		
		// 1. 미세먼지 확산 코드 구현
		for(int i = 0; i < T; i++) {
		    // 매 반복문마다 arrCopy 초기화
		    arrCopy = new int[R][C];
		    diffuse();
    		airSimulation();
    		
    		// 현 상태 복사
    		for(int y = 0; y < R; y++) {
    		    for(int x = 0; x < C; x++) {
    		        arr[y][x] = arrCopy[y][x];
    		    }
    		}
    		
		}
		
		// T번의 횟수 더하면 미세먼지 모두 더함
		for(int i = 0; i < R; i++) {
		    for(int j = 0; j < C; j++) {
		        if(arr[i][j] > 0) result+=arr[i][j];
		    }
		}
		
		System.out.println(result);

	}
	
    static void airSimulation() {
        int top = cleanArrY.get(0); // 공기청정기 윗 부분좌표며,  반시계 방향으로 진행 
 
        for (int x = top - 1; x > 0; x--) {
            arrCopy[x][0] = arrCopy[x - 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            arrCopy[0][y] = arrCopy[0][y + 1];
        }
 
        for (int x = 0; x < top; x++) {
            arrCopy[x][C - 1] = arrCopy[x + 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            arrCopy[top][y] = arrCopy[top][y - 1];
        }
 
        arrCopy[top][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.
 
        int bottom = cleanArrY.get(1); // 공기청정기 밑 부분좌표며, 시계방향으로 진행
 
        for (int x = bottom + 1; x < R - 1; x++) {
            arrCopy[x][0] = arrCopy[x + 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            arrCopy[R - 1][y] = arrCopy[R - 1][y + 1];
        }
 
        for (int x = R - 1; x > bottom; x--) {
            arrCopy[x][C - 1] = arrCopy[x - 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            arrCopy[bottom][y] = arrCopy[bottom][y - 1];
        }
 
        arrCopy[bottom][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.
    }
    
	// 미세먼지 확산 코드
	static void diffuse() {
	    // 우선 기존의 배열의 값을 공식을 통해서
	    // 새로운 배열로 옮겨준다
	    // 그 후 새로운 배열의 값을 기존의 값으로 옮겨주기!
	    for(int y = 0; y < R; y++){
	        // 1부터 시작
	        for(int x = 0; x < C; x++) {
	            //  각 배열마다 값 옮겨주기
	            if(arr[y][x] == 0) continue;
	            int cnt = 0; // 몇 방향으로 옮겨갔는지
	            for(int i = 0; i < 4; i++) {
	                
	                int ny = y + dy[i];
	                int nx = x + dx[i];
	                // 범위 안에 있고, 공기청정기가 없다면
	                if(isIn(ny, nx) && canMove(ny, nx)) {
	                    cnt++;
	                    // 기존 값을 공식을 통해서 새 위치로 옮겨줌(더해야 함)
	                    arrCopy[ny][nx] += arr[y][x] / 5;
	                }
	                
	            }
	            // 4방향 탐색이 끝나고 옮겨준 후 남은 배열
	            arrCopy[y][x] += arr[y][x] - (arr[y][x]/5 * cnt);
	        }
	    }
	    
	}
	
	// 범위 밖으로 나가지 않는지 확인하는 코드
	static boolean isIn(int y, int x){
	    return 0 <= y && y < R && 0 <= x && x < C;
	}
	
	// 미세먼지가 움직일 수 있나 벽 존재 확인 코드
	static boolean canMove(int y, int x) {
	    return arr[y][x] != -1; 
	}

	
}
