import java.util.*;
import java.io.*;

public class Main
{
    // int 가능(250000 * 8000)
    static int N, X;
    static int[] arr;
    static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫 번째 Pivot계산
		int tmpSum = 0;
		for(int i = 0; i < X; i++) {
		    tmpSum += arr[i];
		}
		
		dp[X-1] = tmpSum;
		
		int result = dp[X-1];
		int cnt = 1;
		// 이제 누적합을 통해서 계산
		for(int i = X; i < N; i++) {
		    dp[i] = dp[i-1] + arr[i] - arr[i-X];
		    if(dp[i] > result) {
		        result = dp[i];
		        cnt = 1;
		    } else if(dp[i] == result){
		        cnt++;
		    }
		    
		}
		
		if(result == 0) {
		    System.out.println("SAD");
		} else {
		    System.out.println(result);
		    System.out.println(cnt);
		}
		
		
		
	}
}