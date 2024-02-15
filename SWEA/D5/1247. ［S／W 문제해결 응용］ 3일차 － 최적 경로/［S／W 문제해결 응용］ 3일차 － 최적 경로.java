import java.util.*;
import java.io.*;

class Solution{
    static int N;
    static int[] firm; // 출발
    static int[] myHome; // 도착
    static int[][] customer;
    static boolean[] visited;
    static int min;
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 입력
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
		    
		    min = Integer.MAX_VALUE; // 정답
		    firm = new int[2];
		    myHome = new int[2];

		    // 고객 수 입력
		    N = Integer.parseInt(br.readLine());
		    
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    // 회사 위치
		    
		    firm[1] = Integer.parseInt(st.nextToken()); // y값부터 입력
		    firm[0] = Integer.parseInt(st.nextToken());
		    // 내 집 위치
		    
		    myHome[1] = Integer.parseInt(st.nextToken()); // y값부터 입력
		    myHome[0] = Integer.parseInt(st.nextToken());


            
            // 고객의 좌표
            customer = new int[N][2]; // (y, x)
            
            // 좌표 입력
		    for(int i = 0; i < N; i++) {
		         customer[i][1] = Integer.parseInt(st.nextToken());
		         customer[i][0] = Integer.parseInt(st.nextToken());
		    }
		  //  System.out.println(customer[0][0]);
		  //  System.out.println(customer[0][1]);		    
		    // 모든 순열 구하기
		    visited = new boolean[N];
		    perm(0, 0, firm[0], firm[1]);
		    System.out.printf("#%d %d%n", test_case, min);
		    
		}
		
	}
	static void perm(int cnt, int dist, int startY, int startX) {
	    
	    if(cnt == N) {
	        int goHome = calc(myHome[0], myHome[1], startY, startX);
	        min  = Math.min(min, dist + goHome);
	        return;
	    }
	    for(int i = 0; i < N; i++) {
	        if(!visited[i]) {

	            visited[i] = true;
	            int nextY = customer[i][0];
	            int nextX = customer[i][1];
	            int addDist = calc(nextY, nextX, startY, startX);
	            perm(cnt+1, dist + addDist, nextY, nextX);
	            visited[i] = false;
	        }
	    }
	}
	static int calc(int y1, int x1, int y2, int x2) {
	    return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
}

