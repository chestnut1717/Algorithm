import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * @see <a href="https://www.acmicpc.net/problem/1620">
 * - 문제 : SWEA 2001-파리퇴치
 * - 소요시간 : 15분
 * - 난이도 : 하
 * - 아이디어 : 탐색해야 하는 N, M이 작아서 단순히 완전탐색만 하면 된다.
 *   - 완전탐색을 할 때, 파리채의 크기(M*M)에 따라 잡을 수 있는 파리의 최댓값을 반복문을 통해 지속적으로 갱신
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken()); // 영역의 행, 열 크기
            int M = Integer.parseInt(st.nextToken()); // 파리채 크기
            int[][] arr = new int[N][N];
            
            // arr에 입력 받음
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());  
                }
            }
            int max = Integer.MIN_VALUE; // 최댓값을 얻어줘야 하므로 초기값으로 int의 최솟값(약 -20억)으로 초기화
            
            for(int y = 0; y <= N-M; y++) { // 사각형의 좌상단 꼭짓점을 기준으로 탐색함((M-N+1)^2회 탐색)
                for(int x = 0; x <= N-M; x++) {
                    int tmpMax = findSum(arr, y, x, M); 
                    max = Math.max(tmpMax, max); // 최댓값 갱신
                }
            }
            
            
            System.out.printf("#%d %d%n", test_case, max); // 매 테스트케이스마다 정답 출력
		}
	}
	// 특정 구간에서 파리채로 잡을 수 있는 파리 계산
	static int findSum(int[][] arr, int y, int x, int M) { 
	    int result = 0;
	    for(int i = y; i < y + M; i++) {
	        for(int j = x; j < x + M; j++) {
	            result += arr[i][j];
	        }
	    }
	    return result;
	}
}