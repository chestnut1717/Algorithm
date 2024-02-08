import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 5215 햄버거 다이어트
 * - 소요시간 : 30분 
 * - 난이도 : 하
 * - 아이디어 : 정해진 칼로리를 넘지 않고, 모든 조합을 고려해최대의 누적합을 구하는 문제
 * - N이 최대 20이므로 모든 조합을 고려해도 최대 2^20개의 조합을 고려하면 된다.
 * - 칼로리의 상한선과 점수를 조합이 다 만들어졌을때 구하는 게 아닌,누적 칼로리, 누적 점수를 고려하면 매 재귀마다 백트래킹도 가능하다.
 * -
 */
class Solution
{
    static int N;
    static int L;
    static int[][] arr;
    static int[] number;
    static int result;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            arr = new int[N][2]; // arr을 매 테스트케이스마다 초기화
            result = Integer.MIN_VALUE; // result를 매 순간 초기화
            
            // N개의 재료 입력
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken()); // 맛에 대한 점수
                arr[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
            }
            
            // 이제 nC1 ~ nCn까지조합을 만들어 조회해 보자
            for(int r = 1; r <= N; r++) {
                combi(N, r, 0, 0, 0, 0);
            }
            
            System.out.printf("#%d %d%n", test_case, result);

		}
	}
	
	// 조합 문제
	static void combi(int n, int r, int start, int cnt, int calSum, int pointSum) {
	    if(r == cnt) {
	        result = Math.max(result, pointSum); // 최댓값 있나 갱신해준다.
	        return;
	    }
	    
	    // 상항 sum 계산해서 초과가 되지 않는지 확인
	    for(int i = start; i < n; i++) {
	        int point = arr[i][0];
	        int cal = arr[i][1];
	        if (calSum + cal > L) continue; // 칼로리가초과과 되어 버리면, 무시를 한다.(백트래킹)
	        combi(n, r, i+1, cnt+1, calSum+cal, pointSum+point); // 조건을 만족하면 계속해서 진행을 한다.
	    }
	}
}