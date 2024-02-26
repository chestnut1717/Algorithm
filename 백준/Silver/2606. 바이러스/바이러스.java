
import java.io.*;
import java.util.*;

public class Main {
	static int N; // 사람 수
	static int M;
	static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
	static boolean[] visited;
	static Queue<Integer> q = new ArrayDeque<>();
	static int result = 0; // 정답
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N+1; i++) {
			arr.add(new ArrayList<>());
		}
		visited = new boolean[N+1];
		
		// 사람 관계 입력 받기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 양방향이므로
			arr.get(from).add(to);
			arr.get(to).add(from);
			
		}
		
		// bfs를 통해 탐색
		// 우선 1 넣기
		insertQ(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			if(!visited[tmp]) {
				visited[tmp] = true;
				result++; // 아는 사람 추가
				insertQ(tmp);
				
			}
		}
		
		System.out.println(result);
		
		
		

	}
	
	static void insertQ(int from) {
		for(int i: arr.get(from)) {
			q.offer(i);
		}
	}

}

//class Pair{
//	int from, to;
//	public Pair(int from, int to) {
//		this.from = from;
//		this.to = to;
//	}
//}