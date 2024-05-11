import java.util.*;
import java.io.*;

public class Main
{
    static int T;
    static List<Integer> testList = new ArrayList<>();
    static long [][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		// 우선 DP로 미리 판을 깔아놓는다.
		
		
		// 테스트 케이스만큼 반복
		int maxN = -1;
		for(int i = 0; i < T; i++) {
		    // 우선 값 넣는다.
		    int n = Integer.parseInt(br.readLine());
		    testList.add(n);
		    maxN = Math.max(maxN, n); // n 갱신
		}
		
		// 그리고 maxN+1만큼 arr 할당
		dp = new long[maxN+1][11]; // 행 : 0 ~ maxN, 열 : 0~9 + 합
		dp[1][10] = 10;
		// 일단 row가 1일 때 초기화
		long tmpSum = 0;
		for(int col = 0; col < 10; col++) {
		    dp[2][col] = 10 - col;
		    tmpSum += dp[2][col];
		}
		// 마지막은 이제까지 더한 값을 모두 구함!
		dp[2][10] = tmpSum;
		
		// DP table을 채워넣자
		for(int row = 3; row <= maxN; row++) {
		    // 다음 row의 0번째값 = row-1번째의 줄어들지 않은 경우의 수
		    dp[row][0] = dp[row-1][10];
		    tmpSum = dp[row][0];
		    for(int col = 1; col <= 9; col++) {
		        dp[row][col] = dp[row][col-1] - dp[row-1][col-1];
		        tmpSum += dp[row][col];
		    }
		    
		    // 그다음 n번째 줄어들지 않은 수 갱신
		    dp[row][10] = tmpSum;
		}
		
		// 정답 출력
		for(int n: testList) {
		    sb.append(dp[n][10]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
}
