import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : swea 1210 - Ladder1
 * - 아이디어 : 사다리의 도착 지점에서부터 출발 지점으로 올라가는 방법 구현
 *   1. 필요 없는 경우를 탐색하지 않고, 방향이 바뀌면 바로 그 방향으로 갈 수 있게끔 조건식을 재귀 안에 넣음
 *   2. 방향이 바뀌는 경우는, 다음 재귀함수를 호출할 때, 그 방향으로 가도록 방향을 바꿔주고, 그렇지 않으면 그대로 갈 수 있도록 한다.
 */

class Solution
{    
    // 아래쪽은 탐색하는 경우가 없으므로 3방탐색 
    static final int[] dy = {0, 0, -1};
    static final int[] dx = {1, -1, 0};
    static int N = 100; // 2차원 배열은 100 * 100으로 고정
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = 10; // 테스트 케이스 탐
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int tc = Integer.parseInt(br.readLine()); // 테스트케이스 번호 입력
            int[][] arr = new int[N][N]; // 크기가 100으로 고정
            int startY = 0;
            int startX = 0;
            
            // 사다리 입력
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 2) {
                        startY = i; startX = j; // 미리 출발점 계산(2를 미리 찾아놓음)
                    }
                }
            }
            
            // 재귀를 통해서 도착 지점 탐
            int result = ladderUp(arr, startY, startX, 2);
            System.out.printf("#%d %d%n", tc, result);
        }
    }
    // 사다리를 타고 도착지점에서 출발지점으로 올라가는 재귀함수
    static int ladderUp(int[][] arr, int y, int x, int direction) {
    	// 종료조건! (y가 0이면 자동으로 끝남) 
    	if (y == 0) {
    		return x;
    	}

    	int ny = 0;
    	int nx = 0;
    	boolean flag = false; // direction이 변했는지 판별하는 여부(안바뀌면 그대로 위로 직진)
    	
    	if (direction == 2) {
    		// 오른쪽, 왼쪽 탐색함 
    		for(int i = 0; i < 2; i++) {
    			int tmpY = y + dy[i];
    			int tmpX = x + dx[i];
    			// 만약 범위 내에 있고, 해당 숫자가 1이라면
    			if(isIn(tmpY, tmpX) && arr[tmpY][tmpX] == 1) {
    				ny = tmpY;
    				nx = tmpX;
    				direction = i; // 방향을 바꿔주고, 
    				flag = true; // flag도 변경 
    				break;
    			}
    		}

    	}
    	
    	// 왼쪽 탐색함 
    	else if (direction == 1) {
			int tmpY = y + dy[1];
			int tmpX = x + dx[1];
			// 만약 범위 내에 있고, 해당 숫자가 1이라면
			if(isIn(tmpY, tmpX) && arr[tmpY][tmpX] == 1) {
				ny = tmpY;
				nx = tmpX;
				direction = 1; // 방향을 바꿔주고, 
				flag = true; // flag도 변경 
			}
    	}
    	// 오른쪽탐색함 
    	else if (direction == 0) {
			int tmpY = y + dy[0];
			int tmpX = x + dx[0];
			
			if(isIn(tmpY, tmpX) && arr[tmpY][tmpX] == 1) {
				ny = tmpY;
				nx = tmpX;
				direction = 0;
				flag = true;
			}
    	}
    	
    	// 방향이 바뀌지 않는 경우라
    	if (flag == false) {
    		ny = y + dy[2];
    		nx = x + dx[2];
    		direction = 2;
    	}
    	// 새로운 지점 탐색 
    	return ladderUp(arr, ny, nx, direction);
    	
    	
        
    }
    
    // 해당 좌표값이 올바른 범위 내에 있는가 판별하는 함수 
    static boolean isIn(int y, int x) {
    	return (0 <= y && y < N && 0 <= x && x < N);
    }
    

    
}