import java.io.*;
import java.util.*;

public class Main {
    static int N, K; // 물품의 수, 버틸 수 있는 무게
    static int[][] dp;
    static int[] weights;
    static int[] values;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    
	    // N개의 짐 입력받기
	    
	    weights = new int[N+1]; // [0, 0, 0, 0, 0]
	    values = new int[N+1]; // [0, 0, 0, 0, 0]
	    for(int i = 1; i <= N; i++) {
	        st = new StringTokenizer(br.readLine());
	        weights[i] = Integer.parseInt(st.nextToken());
	        values[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    dp = new int[N+1][K+1]; // 1번째부터 배열 상에 나타낼 것이므로
	    
	    // DP를 활용해서 풀기
	    int result = Integer.MIN_VALUE;
	    for(int i = 1; i <= N; i++) {
	        for(int j = 1; j <= K; j++) {
	            int w = weights[i]; // i번째 물품의 무게
	            if(w > j) {
	                dp[i][j] = dp[i-1][j];
	            } else {
	                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + values[i]);
	            }
	            result = Math.max(result, dp[i][j]);
	        }
	    }
	    
	    System.out.println(result);
	    
	}
}