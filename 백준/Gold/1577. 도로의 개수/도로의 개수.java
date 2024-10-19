import java.util.*;
import java.io.*;
public class Main
{
    static int N, M;
    static int K;
    static int[][][] arr;
    static long[][] dp;
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(br.readLine());
	    
	    // 우선 array 초기화
	    arr = new int[M+1][N+1][2];
	    dp = new long[M+1][N+1];
	    

	    
	    // K개의 도로 공사 입력
	    for(int i = 0; i < K; i++) {
	        st = new StringTokenizer(br.readLine());
	        int x1 = Integer.parseInt(st.nextToken());
	        int y1 = Integer.parseInt(st.nextToken());
	        int x2 = Integer.parseInt(st.nextToken());
	        int y2 = Integer.parseInt(st.nextToken());
	        
	        // y1 == y2가 같다면  => 가로로 금지된 것
	        if(y1 == y2) {
	            arr[y1][Math.min(x1, x2)][1] = 1;
	        }
	        // x1 == x2 => 세로로 금지된 것
	        else {
	            arr[Math.min(y1, y2)][x1][0] = 1;
	        }

	    }
	    
	    // 세로로 초기화
	    for(int y = 1; y < M+1; y++) {
	        if(arr[y-1][0][0] == 1) break; // 더 이상 할 필요가 없음
	        dp[y][0] = 1;
	    }
	    
	    // 가로 초기화
	    for(int x = 1; x < N+1; x++) {
	        if(arr[0][x-1][1] == 1) break; // 더 이상 할 필요가 없음
	        dp[0][x] = 1;
	    }
        
        // 이제 DP를 통해 탐색
        for(int i = 1; i < M+1; i++) {
            for(int j = 1; j < N+1; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                // 만약 가로로 금지되어 있다면
                if(arr[i][j-1][1] == 1) dp[i][j] -= dp[i][j-1];
                // 만약 세로로 금지되어 있다면
                if(arr[i-1][j][0] == 1) dp[i][j] -= dp[i-1][j];
            }
        }
        
        System.out.println(dp[M][N]);
	    

		
	}
}