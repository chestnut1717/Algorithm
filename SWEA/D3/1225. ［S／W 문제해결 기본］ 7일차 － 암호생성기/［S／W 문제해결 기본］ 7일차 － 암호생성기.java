import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 16744. 마이쭈시뮬레이션_실습
 * - 소요시간 : 20분 
 * - 난이도 : 상
 * - 1. 큐를 활용해 간단히 해결 가능. 
 * - 2. 맨 앞의 값을 추출 후 일련의 규칙에 따라 뺌 => 뺀 값이 0보다 작거나 같으면 => 큐에 0을 넣고 반복문 중단 => 암호문 출력
 * - 3. 그렇지 않다면 뺀 값을 큐에 다시 넣음
 */
class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		/* 테스트 케이스 돌아감 */
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Queue<Integer> q = new ArrayDeque<>();
			
			// 초기 숫자 넣기
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
		
			boolean flag = true;
			while (flag) {
				// 사이클 시작
				for(int i = 1; i <= 5; i++) {
					int tmp = q.poll() - i; // 값 감소
					if(tmp <= 0) { // 뺀 값이 0보다 작거나 같으면
						q.add(0);
						flag = false; // while문을 종료시켜줘야 하기 때문에
						break;
					} else {
						q.add(tmp); // 뺀 값을 더한다
					}
				}
			}
			// 정답 출력!
			System.out.printf("#%d ", test_case);
			for(int num: q) {
				System.out.printf("%d ", num);
			}
			System.out.println();
		
		}
	}
}