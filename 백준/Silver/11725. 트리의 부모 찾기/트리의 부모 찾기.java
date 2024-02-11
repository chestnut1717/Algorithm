import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 11725 트리의 부모 찾기
 * - 소요시간 : 30분
 * - 난이도 : 중
 * - 아이디어 : map을 활용한 인접 리스트와 BFS를 활용해서 풀 수 있다.
 * - 먼저, 루트가 1이라고 가정되어 있으므로 1번 노드를 먼저 Queue에 넣어서 BFS를 진행한다.
 * - 큐에 저장된 노드를 하나씩 꺼내면서, 노드와 연결된 다른 노드들을 연결 리스트에서 추출한다.
 * - 그 후, 연결 리스트에서 추출된 노드를 다음과 같은 조건을 통해 탐색
 *  1. 루트 노드가 아닌가
 *  2. 이미 부모 노드를 발견한 노드가 아닌, 탐색하지 않는 노드인가
 * - 해당 조건 일치하면 => parent 배열에 index를 기준으로 부모를 등록해주고, 다시 해당 노드를 큐에 넣어서 하향식 탐색 반복
 */
public class Main {
	static int N;
	static Map<Integer, List<Integer>> map = new HashMap<>();
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// map에 존재하면 기존 ArrayList에 append해줌
			// 그렇지 않으면 put
			if (!map.containsKey(a))
				map.put(a, new ArrayList<>());
			map.get(a).add(b);

			if (!map.containsKey(b))
				map.put(b, new ArrayList<>());
			map.get(b).add(a);
		}
		Queue<Integer> q = new ArrayDeque<>(); // 트리를 탐색할 큐(for BFS)

		q.offer(1); // 우선 노드 1 넣어줌
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i : map.get(now)) {
				if (i != 1 && parent[i] == 0) { // i가 루트 노드가 아니고(부모 노드가 없음), 부모를 아직 안 발견했다면
					parent[i] = now; // i의 부모를 now로 설정
					q.offer(i); // i에서 관계 지속적으로 맺어줌!
				}
			}
		}

		// 출력
		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}

	}
}
