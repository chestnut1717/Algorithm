// 08:55
import java.util.*;
import java.io.*;
public class Main
{
    static int N, K;
    static int[][] map;
    static int cnt; // 내구도 0인 거 개수 세기
    static int stage;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// map 초기화
		map = new int[N*2][2];
		
		// 내구도 주어짐
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N * 2; i++) {
		    map[i][0] = Integer.parseInt(st.nextToken());
		}

		// 이제 이것을 진행한다.
		while(cnt < K) {
		    stage++;
		    // 1. 우선 시계방향으로 컨베이어벨트 돌린다
		    turn();
		    // 2. 그리고 로봇의 위치를 변경한다.
    	    for (int i = N - 2; i >= 0; i--) { // 로봇을 내리는 위치 바로 앞부터 처리
                if (map[i][1] == 1 && map[i + 1][1] == 0 && map[i + 1][0] > 0) {
                    map[i + 1][1] = 1; // 로봇 이동
                    map[i][1] = 0; // 현재 위치 로봇 제거
                    map[i + 1][0]--; // 내구도 감소
    
                    if (map[i + 1][0] == 0) {
                        cnt++;
                    }
                }
            }

    	    // 만약 N-1번째에 있다면 로봇 제거
    	    if(map[N-1][1] == 1) {
    	        map[N-1][1] = 0;
    	    }

		    
		    // 3. 그리고 0번째에 로봇을 올린다
		    if(map[0][0] >= 1) {
		        map[0][1] = 1; // 로봇 세팅
		        map[0][0]--; // 내구도감소
		        // 내구도 체크
		        if(map[0][0] == 0) {
	                cnt++;
	            }
		        
		    }
		    
		}
		
		System.out.println(stage);

	}
	
	
	// 컨베이어벨트 회전
	static void turn() {
	    int tail0 = map[2*N-1][0];
	    int tail1 = map[2*N-1][1];
	    
	    for(int i = 2*N-1; i > 0; i--) {
	        map[i][0] = map[i-1][0];
	        map[i][1] = map[i-1][1];
	    }
	    
	    map[0][0] = tail0;
	    map[0][1] = tail1;
	    
    	// 만약 N-1번째에 있다면 로봇 제거
	    if(map[N-1][1] == 1) {
	        map[N-1][1] = 0;
	    }
	}
}