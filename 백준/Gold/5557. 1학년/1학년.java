import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 숫자 개수
    static int[] arr; // 0~9위 수를 담는 숫자
    static long[][] dp; // 개수가 2^63-1개가 최대이므로
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    // N개의 숫자 입력
	    arr = new int[N];
	    for(int i = 0; i < N; i++) {
	        arr[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    // dp 배열 초기화
	    dp = new long[N][21];
	    
	    // 초기값 설정
	    dp[0][arr[0]] = 1;
	    
	    for(int i = 1; i < N-1; i++) {
	        for(int j = 0; j <= 20; j++) {
	            if(dp[i-1][j] == 0) continue;
	            else {
	                int plus = j+arr[i];
                    int minus = j-arr[i];
	                if(minus >= 0) dp[i][minus] += dp[i-1][j];
	               // if(j - arr[i] >= 0) dp[i][j - arr[i]] = dp[i-1][j];
	                if(plus <= 20) dp[i][plus] += dp[i-1][j];
	            }
	        }
	    }
	    
	    
	    System.out.println(dp[N-2][arr[N-1]]);
	}
}