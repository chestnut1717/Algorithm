import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, -1, 0};
    static final int[] dx = {-1, 0, 1};
    static int N, M, D;
    static int[][] map; // 맵 정보
    static int[][] mapCopy; // 맵 백업본
    static int[] numbers = new int[3]; // 배열에 사용되는 단어
    static List<List<Integer>> combiList = new ArrayList<>();
    static int result;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 거리
		
		// 맵 정보 입력
		map = new int[N][M];
		mapCopy = new int[N][M];
		
		for(int y = 0; y < N; y++) {
		    st = new StringTokenizer(br.readLine());
		    for(int x = 0; x < M; x++) {
		        map[y][x] = Integer.parseInt(st.nextToken());
		    }
		    
		}

		// 우선 모든 조합을 생성하기
		combination(0, 0);
		
		// 해당 조합을 가지고 진행하기
		copy(); // 우선 map은 재사용하기 때문에 미리 복사해놓음
		for(List<Integer> combi: combiList) {
		    int tmpResult = 0;
		    int cnt = 0;
		    // while문을 통해 wave 진행(N개의 col이니깐 최대 N번 진행)
		    while(cnt < N) {
		        tmpResult += wave(combi);
		        // 웨이브가 끝날 때마다 맵 바꿔준다.
		        changeMap();
		        cnt++;
		    }
		   
		    result = Math.max(result, tmpResult);
		    // 해당 조합의 경우가 끝났으므로 다시 백업본을 복구해준다.
		    for(int i = 0; i < N; i++) {
		        for(int j = 0; j < M; j++) {
		            map[i][j] = mapCopy[i][j];
		        }
		    }
		}
		
		// 해답 출력
		System.out.println(result);

	}
	
	static void copy() {
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < M; j++) {
	            mapCopy[i][j] = map[i][j];
	        }
	    }
	}
	
	// 궁수가 가장 가까운 적을 찾는 코드
	static Pair bfs(int archerY, int archerX) {
	    Queue<Pair> q = new ArrayDeque<>();
	    // 거리 가까운 적들 저장하는 용도!
	    boolean[][] visited = new boolean[N][M]; // 방문 배열
	    
	    // 해당 궁수 맨 앞에 있는 것 방문하기
	    q.offer(new Pair(archerY-1, archerX));
	    visited[archerY-1][archerX] = true;
	    // 만약 궁수 바로 앞이적이라면, 바로 반환 해주기(가장 가까울 수밖에 없음)
	    if(map[archerY-1][archerX] == 1) {
	        return new Pair(archerY-1, archerX);
	    }
	    
	    int limit = 1;
	    // 큐가 비지 않으면서, 제한 거리를 넘을 때 진행!
	    while(!q.isEmpty()) {
	        Pair nextLocation = q.poll();
	        int y = nextLocation.y;
	        int x = nextLocation.x;
	        // 위로만 가므로 3방탐색이다!
	        for(int i = 0; i < 3; i++) {
	            int ny = y + dy[i];
	            int nx = x + dx[i];
	            
	            // 방문하지 않았으면
	            if(isIn(ny, nx) && !visited[ny][nx]) {
	                visited[ny][nx] = true;
	                limit = calcDist(archerY, ny, archerX, nx); // limit갱신

	                // 궁수가 쏠 수 있는임계치를 초과했을 때
	                if(limit > D) {
	                    return null;
	                }
	                
	                // 만약 그 위치에 적이 있다면 => 바로 출력(왜냐하면 가장 왼쪽에 있는 것을 치므로)
	                if(map[ny][nx] == 1) {
	                    return new Pair(ny, nx);
	                }
	                else {
	                    q.offer(new Pair(ny, nx));
	                }
	            }
	        }
	    }
	    // 큐가 빌 때까지 맞출 적이 없었다는 것을 의미
	    return null;
	    
	    
	}
    static void changeMap() {
        for(int y = N-1; y >= 0; y--) {
            for(int x = 0; x < M; x++) {
                if(y == 0) map[y][x] = 0;
                else {
                    map[y][x] = map[y-1][x]; // y-1의 값들을 y로 복사하는 방식
                }
            }
        }
    }

	// 각 wave마다 몇 명 죽이는지 반환
	static int wave(List<Integer> combi) {
	    // 각 wave마다 궁수는 목표를 향한 계산을 해야 한다!
	    //  => 이때 모든 목표에 대한 계산을 해야 함
	    final int archerY = N;
	    int killed = 0;
	    HashSet<List<Integer>> set = new HashSet<>(); // 중복제거용
	    for(int archerX: combi) {
	        // 각 궁수의 좌표를 가지고 가장 가장 가까운 궁수 값 가져오기
	        Pair enemy = bfs(archerY, archerX);
	        if(enemy == null) continue; // 어떠한 적도 없다면 무시
	        int y = enemy.y;
	        int x = enemy.x;
	        // 해당 적의 좌표를 지우기(적이 있을 경우에만 반호나)
	        if(map[y][x] == 1) {
	            set.add(Arrays.asList(y, x));
	            
	        }
	    }
	    // 동시에 쏴야 하므로 중복 제게ㅓ
    	for(List<Integer> lst: set) {
            map[lst.get(0)][lst.get(1)] = 0;
            killed++;
	    }
	    return killed;
	}
	// mC3 모든 조합 계산 함수
	static void combination(int depth, int start) {
	    
	    if(depth == 3) {
	        List<Integer> tmpList = new ArrayList<>();
	        for(int n: numbers) tmpList.add(n);
	        
	        combiList.add(tmpList);
	        return;
	    }
	    for(int i = start; i < M; i++) {
	        numbers[depth] = i;
	        combination(depth+1, i+1);
	    }
	}
	
	// 거리 계산 함수
	static int calcDist(int archerY, int targetY, int archerX, int targetX) {
	    return Math.abs(archerY - targetY) + Math.abs(archerX - targetX);
	}
	
	// 좌표 내에 있나 확인
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static class Pair{
	    int y, x;
	    public Pair(int y, int x){
	        this.y = y;
	        this.x = x;
	    }
	}
}