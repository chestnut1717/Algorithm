import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {0, 0, -1, 1};
    static final int[] dx = {1, -1, 0, 0};
    static int N, K; // 크기, 말 개수
    static int[][] map;
    static int[][] turn;
    static List<List<Deque<Knight>>>dequeMap = new ArrayList<>();
	public static void main(String[] args) throws Exception{
	    
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 문제에서 벽 같은 경우는 파란색과 똑같이 처리한다고 했으므로 벽 부분을 2로 채움
		map = new int[N+2][N+2];
		for(int i = 0; i < N+2; i++) {
		    dequeMap.add(new ArrayList<>()); // Deque 배열 초기화
		    for(int j = 0; j < N+2; j++) {
		        if(i ==0 || j == 0 || i == N+1 || j == N+1) map[i][j] = 2; // 벽은 파란색과 동일 효과
		        dequeMap.get(i).add(new ArrayDeque<>());
		    }
		}
		
		// 입력
		for(int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 1; j <= N; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());

		    }
		}
		
		// K개의 말 입력받기 (1~K번)
		turn = new int[K+1][2];
		for(int num = 1; num <= K; num++) {
		    st = new StringTokenizer(br.readLine());
		    // row, col 1부터 시작하도록 함
		    int y = Integer.parseInt(st.nextToken());
		    int x = Integer.parseInt(st.nextToken());
		    int direction = Integer.parseInt(st.nextToken());
		    // 방향은 -1부터
		    dequeMap.get(y).get(x).offer(new Knight(num, y, x, direction-1));
		    
		    // 매 번 턴을 확인하기 위해서 큐를 활용한다!
		    turn[num][0] = y;
		    turn[num][1] = x;
		}
		
		// 정답 출력
		System.out.println(calc());
	}
	
	static int calc() {
	  int cnt = 0;
	  // 1000번일 때까지
		while (cnt < 1000) {
		    cnt++;

		    for(int num = 1; num <= K; num++) {
		        int y = turn[num][0];
		        int x = turn[num][1];
		        
		        Deque<Knight> nowDeque = dequeMap.get(y).get(x); // 현재 i번이 위치하고 있는 곳의 덱
		        Deque<Knight> tmpDeque = new ArrayDeque<>(); // 이동을 위한 임시 덱
		        
		        // 밑에 것이 아니면 못움직이므로 무시
		        if(nowDeque.peekLast().num != num) continue;
		        // deque에서 num번 knight가 나올때까지 옮기기(스택 느낌으로 옮김)
		        while(true) {
		            tmpDeque.offerFirst(nowDeque.pollFirst());
		            if(tmpDeque.peekFirst().num == num) break; // 찾았으면 break
		            
		        }
		        
		        // 어디로 이동해야 할지 방향 추출
		        int direction = tmpDeque.peekFirst().direction;
		        int ny = y + dy[direction];
		        int nx = x + dx[direction];
		        
		
							
							// 다음 위치로 갈 것을 추출
	            Deque nextDeque = dequeMap.get(ny).get(nx);
	            // 색깔 추출
	            int color = map[ny][nx];
	            switch(color) {
	                case 0: // 하얀색
    		            whiteMove(tmpDeque, nextDeque, ny, nx);
	                    break;
	                case 1: // 빨간색
                        redMove(tmpDeque, nextDeque, ny, nx);
	                    break;
	                case 2: // 파란색
	                    // 우선 방향을 바꿔준다.
                        direction = changeDirection(direction);
                        tmpDeque.peekFirst().direction = direction;
                        // 그 후 ny, nx를 반대방향으로 다시 바꿔준다.
                        ny = y + dy[direction]; // ny, nx가 아닌 y, x임에 주의)
                        nx = x + dx[direction];
                        nextDeque = dequeMap.get(ny).get(nx);
	                    blueMove(tmpDeque, nowDeque, nextDeque, ny, nx);
	                    break;
	                    

	            }
	            // 종료조건!
		        if (dequeMap.get(ny).get(nx).size() >= 4) {
		           return cnt;
		        }
		    }
		}
		
		return -1;
	}
	
	static void changeLocation(int num, int y, int x) {
	    turn[num][0] = y;
		turn[num][1] = x;
	}
	
	static void whiteMove(Deque<Knight> tmpDeque, Deque<Knight> nextDeque, int ny, int nx) {
	    while(!tmpDeque.isEmpty()) {
            Knight tmpKnight = tmpDeque.pollFirst(); // 뒤에서부터 하나씩 뽑아준다.
            int num = tmpKnight.num;
            nextDeque.offerFirst(tmpKnight); // 앞에서부터 넣어준다.
            changeLocation(num, ny, nx);
        }
	}
	
	static void redMove(Deque<Knight> tmpDeque, Deque<Knight> nextDeque, int ny, int nx) {
           while(!tmpDeque.isEmpty()) {
            Knight tmpKnight = tmpDeque.pollLast();
            int num = tmpKnight.num;
            nextDeque.offerFirst(tmpKnight);
            changeLocation(num, ny, nx);
        }
	}

	static void blueMove(Deque<Knight> tmpDeque, Deque<Knight> nowDeque, Deque<Knight> nextDeque, int y, int x) {
        switch(map[y][x]) {
            case 0:
                whiteMove(tmpDeque, nextDeque, y, x);
            case 1:
                redMove(tmpDeque, nextDeque, y, x);
                break;
            case 2:
		            // 만약 새로 탐색하려는 곳도 파란색(or 벽)이면 제자리에 넣어줌
                while(!tmpDeque.isEmpty()) {
                    Knight tmpKnight = tmpDeque.pollFirst();
                    int num = tmpKnight.num;
                    nowDeque.offerFirst(tmpKnight); // 앞에서부터 넣어준다.
                }
                break;
        }

	}
	
	
	static int changeDirection(int direction) {
	    switch(direction) {
	        case 0 : return 1;
	        case 1 : return 0;
	        case 2 : return 3;
	        case 3 : return 2;
	        default: return -1;
	    }
	}
	
	// 말을 저장하기 위한 클래스
	static class Knight {
	    int num, y, x, direction;
	    public Knight(int num, int y, int x, int direction) {
	        this.num = num;
	        this.y = y;
	        this.x = x;
	        this.direction = direction;
	    }
	}
	
}