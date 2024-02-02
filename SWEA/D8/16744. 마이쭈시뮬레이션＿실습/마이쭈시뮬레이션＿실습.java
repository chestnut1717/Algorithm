import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 16744. 마이쭈시뮬레이션_실습
 * - 소요시간 : 60분 
 * - 난이도 : 상
 * - 1. 우선 첫 번째 큐에 값을 넣어준다.
 * - 2. 그 후 뺀 사람은 바로 1개를 더 가져가기 위해 다시 기다리고 => 다음 순번의 사람이 1개 먹는 과정을 반복문으로 구현
 * - 3. 마이쮸가 다 소진될때까지 반복
 */
class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= 10; test_case++) {

			int myChew = Integer.parseInt(br.readLine()); // 마이쮸 개수 입력
			Queue<List<Integer>> queue = new ArrayDeque<>(); // 큐 생성

			int result = find(myChew, queue); // 마지막으로 받은 사람의 순번 반환
			System.out.printf("#%d %d%n", test_case, result);
		}
	}
	
    // 마지막 마이쮸를 먹은 사람의 번호를 반환
	static int find(int myChew, Queue<List<Integer>> queue) {

		int maxPersonNum = 1; // 순번 
		while (myChew > 0) {

			if (queue.isEmpty()) {
				queue.add(Arrays.asList(maxPersonNum, 1)); // 1번 우선 넣어줌
				continue;
			}

			
			// 큐에 담긴 사람을 빼준다.
			List<Integer> tmpList = queue.poll();
			myChew -= tmpList.get(1);
			

			// 혹시나 그 사람이 마지막으로 받아갔으면(남은 마이쮸의 개수가 0 이하) => 그 사람 순번 반환
			if (myChew <= 0) {
				return tmpList.get(0);
			}
			
			// 한번 받은 사람이 다시 받을 때는, 지난번에 받은 개수 + 1을 해줘야 하므로 List로 접근
			tmpList.set(1, tmpList.get(1) + 1);
			queue.offer(tmpList);

			// 큐에서 막 나온 사람이 들어갔으면, 다음 순번 사람이 들어간다.
			maxPersonNum++; // 순번 증가
			queue.add(Arrays.asList(maxPersonNum, 1));

		}
		return -1;
	}
}