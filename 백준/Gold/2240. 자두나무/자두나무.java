import java.util.*;
import java.io.*;

public class Main
{
    static int T, W;
    static int[] arr;
    static int[][][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		arr = new int[T+1];
		
		for(int i = 1; i <= T; i++) {
		    arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 이제 dp를 초기화시킨다.
		dp = new int[3][T+1][W+2];
		// dp[현재 나의 위치][시간][남은 움직임 횟수] = 시간, 움직임 횟수를 고려할 때 특정 위치에서 가질 수 있는 최댓값
		for(int t = 1; t <= T; t++ ) {
		    for(int w = 1; w <= W+1; w++) {
		        if(arr[t] == 1) {
		            dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]) + 1;
		            dp[2][t][w] = Math.max(dp[1][t-1][w-1], dp[2][t-1][w]);
		        } else {
		            if(t == 1 && w == 1) continue; // 초기 위치가 1이고, 움직임이 1일 때 => 무시
		            dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]);
		            dp[2][t][w] = Math.max(dp[1][t-1][w-1], dp[2][t-1][w]) + 1;
		        }
		    }
		}
		
		int result = 0;
        for (int i = 1; i <= W + 1; i++) {
            result = Math.max(result, Math.max(dp[1][T][i], dp[2][T][i]));
        }
 
        System.out.println(result);
	}
}