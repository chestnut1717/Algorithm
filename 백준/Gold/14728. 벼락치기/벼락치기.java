import java.util.*;
import java.io.*;
public class Main
{
    static int N, T;
    static int[][] dp;  // dp[i][k] k번째까지 진행했을 때, i번째 시간에서의 최대 배점
    static int[] time; // 시간을 담기 위함
    static int[] value; // 점수를 담기 위함
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    T = Integer.parseInt(st.nextToken());
	    
	    // 배열 초기화
	    dp = new int[N+1][T+1];
	    time = new int[N+1];
	    value = new int[N+1];
	    
	    // 모든 테스트 케이스마다 돌리면
	    for(int i = 1; i <= N; i++) {
	        st = new StringTokenizer(br.readLine());
	        time[i] = Integer.parseInt(st.nextToken()); // 공부시간
	        value[i] = Integer.parseInt(st.nextToken()); // 배점
	    }
	    
	    // 이제 n개의 단원을 고려하여 반복문을 돌아보자
	    for(int i = 1; i <= N; i++) {
	        // 그리고 i개를 고려하는 단원마다 최대 T까지 돌아봐서
	        // dp[i][T]를 구해보자 => dp[i][T] => i번째 개수까지 고려했을 때, 최대 점수
	        for(int j = 1; j <= T; j++) {
	            // 만약 현재 고려하고 있는 단원이 고려하는 시간을 초과해버리면 => 이전거 복사
	            if(time[i] > j) dp[i][j] = dp[i-1][j];
	            // 그렇지 않다면 i번째 단원을 고려하지 않는거랑, 고려한 거중 최댓값 계산 
	            else dp[i][j] = Math.max(dp[i-1][j], value[i] + dp[i-1][j - time[i]]);
	        }
	        
	    }
	    
	    System.out.println(dp[N][T]);
	    
		
	}
}
