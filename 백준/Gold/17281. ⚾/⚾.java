import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 17281 야구
 * - 소요시간 : 1시간 30분 
 * - 난이도 : 상
 * - 메모리 : 82680 KB / 시간 : 696 ms
 * - 아이디어 : 빡구현 + 순열 문제
 * - 선수의 순서를 정하는 전략을 구할 때, 1번타자(해당 코드에서 0)는 무조건 고정시켜놓고 진행해야 시간초과 방지할 수 있음
 * - 경우의 수 : 9!(모든 선수 순열) x 9(이닝의 수) x 27(한 이닝마다 최대 게임의 수) x 3(각 이닝마다 hit할 경우 ru 배열 정리) = 대략 3,000만
 */
public class Main {
	static int N;
	static int[][] game;
	static int[] strategy = { -1, -1, -1, 0, -1, -1, -1, -1, -1 };
	static boolean[] visited = { true, false, false, false, false, false, false, false, false };
	static int maxPoint = Integer.MIN_VALUE;
	static List<Queue<Integer>> allStrategy = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
        
        // N이닝에 담을 게임을 우선 모두 입력한다.
		game = new int[N][9]; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}

		}
	    // 1. 가능한 타자 조합의 수 구하기
		perm(0);
		for (Queue strategy : allStrategy) {
		    int tmpPoint = 0;
		    
			// 2. n이닝 수 플레이
			for(int n = 1; n <= N; n++) {
			    tmpPoint += play(n, strategy);
			}
			
			// 3. 이닝이 끝나면 점수 정산
			maxPoint = Math.max(tmpPoint, maxPoint);
		}
		
		System.out.println(maxPoint);

	}
	// 이닝(n)과 전략(strategy)이 주어지면, 해당 전략 플레이
	static int play(int n, Queue<Integer> strategy) {
	    int out = 0; // 아웃 count
	    int point = 0; // 현재 이닝에서의 점수
	    int[] ru = new int[4]; //1루부터
	    
	    // 각 플레이어마다 게임 짝지어 주기
	    while(out < 3) {
	        int player = strategy.poll(); // 사전에 타자 정한 순서에서 플레이어 하나씩 꺼내기
	        strategy.offer(player); // 다시 다음 주자로 넣어준다!
	        int type = game[n-1][player]; // 해당 플레이어가 공 어떻게 쳤는지(안타, 홈런 등)
	        
	        // out일 경우
	        if (type == 0) {
	            out++;
	            continue;
	        }
	        
	        point += hit(type, ru);
	        
	        
	    }
	    return point; // 해당 이닝에 존재한 점수 모두 반환
	}
	
	// 공을 쳤을 때(1~3루타 + 홈런)
	static int hit(int type, int[] ru) {
	    int point = 0;
	    // 홈런일 경우
	    if (type == 4) {
            for(int j = 1; j <=3 ; j++) {
                point += ru[j];
                ru[j] = 0;
            }
            point += 1; // 자기 자신 점수
            return point;  
	    }
	    // 홈런을 제외한 경우 => 1~3루에 있는 사람 count하고 초기화
	    for(int i = 3; i > 3-type; i--) {
	        point += ru[i];
	        ru[i] = 0;
	    }
	    if(type == 2) {
	        ru[3] = ru[1];
	        ru[1] = 0;
	    }
	    else if(type == 1) {
	        ru[3] = ru[2];
	        ru[2] = ru[1];
	        ru[1] = 0;
	    }
	    ru[type] = 1;
	    
	    return point;
	    
	}
    
    // 0번타자 4번째로 보내기 위해서는!
	static void perm(int depth) {
		if (depth == 9) {
			Queue<Integer> tmp = new ArrayDeque<>();
			for (int i = 0; i < 9; i++) {
				tmp.add(strategy[i]);

			}
			allStrategy.add(tmp);
			return;
		}
		if (depth == 3) {
			perm(depth + 1);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				strategy[depth] = i;
				perm(depth + 1);
				visited[i] = false;
			}

		}

	}
}
