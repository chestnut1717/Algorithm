import java.util.*;
import java.io.*;

public class Main
{
    static int M, N;
    static int[][] arr;
    static int[][] dp; // dp[i][j] = i, j에서 만들 수 있는 최대 정사각형
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		
		// arr 입력
		arr = new int[M+1][N+1];
		dp = new int[M+1][N+1];
		for(int i = 1; i <= M; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 1; j <= N; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// 그리고 dp를 탐색한다.
		for(int i = 1; i <= M; i++) {
		    for(int j = 1; j <= N; j++) {
		        // 만약 정사각형을 만들 수 없다면 무시한다.
		        if(arr[i][j] > 0) continue;
		        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
		        result = Math.max(result, dp[i][j]);
		    }
		}
		
		
		
		System.out.println(result);
    }
}