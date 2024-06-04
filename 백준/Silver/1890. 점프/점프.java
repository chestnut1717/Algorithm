import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[][] arr;
    static long[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// row
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new long[N][N];
		// array elem
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// dp[i][j] = i, j좌표까지 가는 경우까지, 이동할 수 있는 경우의 수
		// 1. 먼저 시작점을 1로 초기화해준다.
		// 2. 모든 정점을 탐색하면서
		// 2.1 만약 해당 값이 1이상이 아니면(갈 수 없으면) 통과한다.
		// 2.2 조건을 만족하는 경우, 이동할 수 있는 경우라면, dp[i][j+val] = dp[i][j](i도 똑같이)
		// 3. dp[N-1][N-1] 출력
		
		dp[0][0] = 1;
		for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(dp[i][j] > 0) {
                    int val = arr[i][j];
                    if(val > 0) {
                        if(isValid(i + val)) dp[i+val][j] += dp[i][j];
                        if(isValid(j + val)) dp[i][j+val] += dp[i][j];
                    }
                    
                }
            }
		}
		
		System.out.println(dp[N-1][N-1]);
	    
		
	}
	
	static boolean isValid(int val) {
	    return val < N;
	}
}
