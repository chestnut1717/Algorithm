import java.util.*;
import java.io.*;
public class Main
{
    static int N, T;
    static int[][] dp;  // dp[i][k] k번째까지 진행했을 때, i번째 시간에서의 최대 배점
    static int[][] arr; // 배열의 정보를 담기 위함
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    T = Integer.parseInt(st.nextToken());
	    
	    // 배열 초기화
	    dp = new int[T+1][N+1];
	    arr = new int[N+1][2]; // arr[i][0] = 공부시간 / arr[i][1] = 배점
	    
	    // 모든 테스트 케이스마다 돌리면
	    for(int i = 1; i <= N; i++) {
	        st = new StringTokenizer(br.readLine());
	        int K = Integer.parseInt(st.nextToken()); // 공부시간
	        int S = Integer.parseInt(st.nextToken()); // 배점
	        
	        arr[i][0] = K;
	        arr[i][1] = S;
	    }
	    
	    // 단원 j개를 공부했을 때
	    for(int i = 1; i <= N; i++) {
	        
	        // 시간 탐색
	        for(int j = 1; j <= T; j++) {
	            if(j >= arr[i][0]) {
	                dp[j][i] = Math.max(dp[j][i-1], arr[i][1] + dp[j-arr[i][0]][i-1]);
	            }
	            else {
	                dp[j][i] = dp[j][i-1];
	            }
	        }
	    }
	    
	    System.out.println(dp[T][N]);
	    
		
	}
}
