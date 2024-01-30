import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * - 문제 : swea 1210 - 농작물 수확하기
 * - 메모리 : 22,940 kb / 시간 : 138  ms
 * - 난이도 : 중(공식 계산하는데 시간 걸림)
 * - 소요시간 : 1시간
 * - 아이디어 : 별 찍기 원리와 비슷하게 어느 부분을 더해줄지 행과 열을 활용해서 출력 가능
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{



		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());


		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N]; // 값을 저장할 2차원 배열 생성
			
			// 배열 저장
			for(int i = 0; i < N; i++) {
				char[] charArr = br.readLine().toCharArray();
					for(int j = 0; j < N; j++) {
						arr[i][j] = charArr[j] - '0'; // 아스키코드 값을 숫자로 변환하기 위해 '0'을 뺀다.
				}
			}
			
			// 반복문으로 탐색
			int result = 0;
			// 0 ~ N/2까지 탐색한다.
			for(int i = N/2; i >= 0; i--) {
				for(int j = N/2-i; j <= N/2+i ; j++) { // 행 인덱스가 줄어들면 열 인덱스가 줄어듦을 구현
					result += arr[i][j];
				}
			}
			// N/2 + 1 ~ N - 1까지 탐색한다.
			for(int i = N/2+1; i < N; i++) {
				for(int j = i-N/2; j <= (N-1) - (i-N/2); j++) { // 행 인덱스가 늘어나면, 열 인덱스가 늘어남을 구현
					result += arr[i][j];
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %d%n", test_case, result);
			

		}
		
	}
}
