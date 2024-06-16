import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[] arr;
    static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// N명의 학생들 입력
		N = Integer.parseInt(br.readLine());

		// 숫자 입력
		arr = new int[N+1];
		dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		    
		}
		
		for(int i = 1; i <= N; i++) {
		    int max = 0, min = 10001;
		    for(int j = i; j > 0; j--) {
		        max = Math.max(max, arr[j]);
		        min = Math.min(min, arr[j]);
		        dp[i] = Math.max(dp[i], max-min+dp[j-1]);
		    }
		}
		
		System.out.println(dp[N]);
	    
	}
	
}