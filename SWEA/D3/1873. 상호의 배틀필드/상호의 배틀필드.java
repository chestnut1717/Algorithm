import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * - 문제 : SWEA 1873 상호의 배틀필드
 * - 소요시간 : 1시간 
 * - 난이도 : 중
 * - 아이디어 : dy, dx를 활용한 구현문제
 * - 중요한 점은 이동 명령어(U, D, L, R)일 때, 움직일 수 없는 경우는 방향만 틀어줘야 한다는 점
 * - savePoint변수를 활용해서 탱크에 있었던 원래 땅이 무엇이었는지 저장, 갱신해줘야 한다.
 */
class Solution {
	static final int[] dy = { 0, 1, 0, -1 };
	static final int[] dx = { 1, 0, -1, 0 };
	static int H, W, N;
	static char[][] oldArr;
	static char[][] arr;
	static int direction;
	static int y, x;
	static char savePoint = '.'; //이전 위치에 무엇이 있었는지 체크해주기 위함

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
		    StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			arr = new char[H][W]; // 초기화
			for (int i = 0; i < H; i++) {
				char[] charArr = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					arr[i][j] = charArr[j];
					// 출발점 찾는 과정
					if (arr[i][j] == '>') {
						direction = 0;
						y = i;
						x = j;
					} else if (arr[i][j] == 'v') {
						direction = 1;
						y = i;
						x = j;
					} else if (arr[i][j] == '<') {
						direction = 2;
						y = i;
						x = j;
					} else if (arr[i][j] == '^') {
						direction = 3;
						y = i;
						x = j;
					}
				}
			}

			N = Integer.parseInt(br.readLine()); // 사용자 넣을 입력
			char[] charArr = br.readLine().toCharArray(); // 문자열!
			for (char c : charArr) {
				if (c == 'U') {
					direction = 3;
					int ny = y + dy[direction];
					int nx = x + dx[direction];
					if (isIn(ny, nx)) {
						char tmp = arr[y][x];
						arr[y][x] = (savePoint == '>' || savePoint == '<' || savePoint == '^' || savePoint == 'v') ? '.'
								: savePoint;
						savePoint = tmp;
						y = ny;
						x = nx;

					}
					// 방향설정 해주기(y, x는 조건에 따라 달라질 수 있으므로 - 벽이 있으면 가지 못하고 방향만 틀어줘야함)
					arr[y][x] = '^';

				} else if (c == 'D') {
					direction = 1;

					int ny = y + dy[direction];
					int nx = x + dx[direction];
					if (isIn(ny, nx)) {
						char tmp = arr[y][x];
						arr[y][x] = (savePoint == '>' || savePoint == '<' || savePoint == '^' || savePoint == 'v') ? '.'
								: savePoint;
						savePoint = tmp;
						y = ny;
						x = nx;

					}
					arr[y][x] = 'v';

				} else if (c == 'L') {
					direction = 2;

					int ny = y + dy[direction];
					int nx = x + dx[direction];
					if (isIn(ny, nx)) {
						char tmp = arr[y][x];
						arr[y][x] = (savePoint == '>' || savePoint == '<' || savePoint == '^' || savePoint == 'v') ? '.'
								: savePoint;
						savePoint = tmp;
						y = ny;
						x = nx;

					}
					arr[y][x] = '<';

				} else if (c == 'R') {
					direction = 0;

					int ny = y + dy[direction];
					int nx = x + dx[direction];
					if (isIn(ny, nx)) {
						char tmp = arr[y][x];
						arr[y][x] = (savePoint == '>' || savePoint == '<' || savePoint == '^' || savePoint == 'v') ? '.'
								: savePoint;
						savePoint = tmp;
						y = ny;
						x = nx;

					}
					arr[y][x] = '>';

				} else if (c == 'S') {
					shoot(y, x, direction);
				}
			}

			// 정답 출력
			sb.append("#" + test_case).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(arr[i][j]);
				}
				sb.append('\n');
			}
            sb.setLength(sb.length() - 1); // 엔터처리 마지막에 한거 없애주기 위함
			System.out.println(sb.toString());

		}
	}

	// 탱크가 이동할 수 있는지 체크
	static boolean isIn(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W && arr[y][x] != '-' && arr[y][x] != '*' && arr[y][x] != '#';
	}

	// 대포 발사
	static void shoot(int y, int x, int direction) {

		int ny = y + dy[direction];
		int nx = x + dx[direction];
		while (0 <= ny && ny < H && 0 <= nx && nx < W) {
			// 벽돌로 만들어진 벽
			if (arr[ny][nx] == '*') {
				arr[ny][nx] = '.';
				return;
			}
			// 강철로 만들어진 벽
			else if (arr[ny][nx] == '#') {
				return;
			}
			// 없다면 계속 폭탄 직진
			ny += dy[direction];
			nx += dx[direction];
		}
	}
}