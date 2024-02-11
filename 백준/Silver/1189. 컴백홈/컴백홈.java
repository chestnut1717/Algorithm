import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 1189 컴백홈
 * - 소요시간 : 40분
 * - 난이도 : 중
 * - 아이디어 : 깊이 우선 탐색을 활용해서 풀 수 있는 문제
 * - 종료 조건을 정해진 거리 K만큼 오고, 도착지가 우측 상단인지 확인하고 탐색하면 된다.
 */
public class Main {
	static final int[] dy = { 0, 1, 0, -1 };
	static final int[] dx = { 1, 0, -1, 0 };
	static int R, C, K; // row, col, k
	static char[][] arr;
	static boolean[][] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// r, c, k 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 배열 입력
		arr = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				arr[i][j] = charArr[j];

			}
		}
		dfs(R - 1, 0, 1); // 시작점에서 출발하는 것은 1부터 시작
		System.out.println(result);

	}

	// dfs
	static void dfs(int y, int x, int cnt) {
		visited[y][x] = true; // 방문처리
		if (cnt == K) {
			if (y == 0 && x == C - 1) { // cnt가 k이고, 현재 위치가 오른쪽 위에 있다면
				result++;
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			// 다음 좌표가 유효한 좌표이고, 아직 방문하지 않았으면서, 장애물이 아닌 것
			if (isIn(ny, nx) && !visited[ny][nx] && arr[ny][nx] != 'T') {
				dfs(ny, nx, cnt + 1);
				visited[ny][nx] = false; // 방문한 후, false처리를 해줘야 다음 반복에서도 탐색 가능
			}
		}

	}
    // grid 내 유효한 좌표인지 체크하는 메서드
	static boolean isIn(int y, int x) {
		return 0 <= y && y < R && 0 <= x & x < C;
	}
}