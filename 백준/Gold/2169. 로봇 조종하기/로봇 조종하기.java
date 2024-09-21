import java.util.*;
import java.io.*;

public class Main
{
    // 오, 아, 왼
    static final int[] dy = {0, 1, 0};
    static final int[] dx = {-1, 0, 1};
    static int N, M;
    static int[][] arr;
    static int[][] dp;
    static int[][] temp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 입력 끝
		arr = new int[N][M];
		dp = new int[N][M];
		temp = new int[2][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// 우선 첫번째줄 모두 왼 => 오밖에 못가니 dp 채워준다.
		dp[0][0] = arr[0][0];
        for(int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j-1]  + arr[0][j];
        }
        
        // 그리고 두번째 줄부터는 이제 왼 => 오 + 오  => 왼 둘다
        for(int i = 1; i < N; i++) {
            
            // 왼 => 오
            // 일단 temp채우기
            temp[0][0] = dp[i-1][0] + arr[i][0];
            for(int j = 1; j < M; j++) {
                temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j]) + arr[i][j];
            }
            
            // 오 => 왼
            temp[1][M-1] = dp[i-1][M-1] + arr[i][M-1];
            for(int j = M-2; j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + arr[i][j];
            }
            
            // temp 중 큰 수를 dp로
            for(int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
            
            
        }
        System.out.println(dp[N-1][M-1]);
		
	}
	


}