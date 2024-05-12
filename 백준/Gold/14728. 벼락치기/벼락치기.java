import java.util.*;
import java.io.*;
public class Main
{
    static int N, T;
    static int[] dp;  // dp[i][k] k번째까지 진행했을 때, i번째 시간에서의 최대 배점
    static int[] time; // 시간을 담기 위함
    static int[] value; // 점수를 담기 위함
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    T = Integer.parseInt(st.nextToken());
	    
	    // 배열 초기화
	    dp = new int[T+1];
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
	        // 뒤에서부터 탐색
	        for(int j = T; j >= time[i]; j--) {
	            if(j >= time[i]) dp[j] = Math.max(dp[j], value[i] + dp[j - time[i]]);
	          
	        }
	        
	    }
	    
	    System.out.println(dp[T]);
	}
}
