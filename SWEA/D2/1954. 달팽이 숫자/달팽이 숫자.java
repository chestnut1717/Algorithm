import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
    // 4방탐색을 위한 계산
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
		    int N = Integer.parseInt(br.readLine()); // 달팽이의 크기 입력
            int[][] arr = new int[N][N]; // 크기에 따른 배열 선언 및 초기화
            boolean[][] visited = new boolean[N][N]; // 방문 여부 체크해주는 배열 생성
            
            // 초기값 세팅
            int y = 0;
            int x = 0;
            int direction = 0;
            visited[y][x] = true; // 방문 처리
            arr[y][x] = 1;
            
            for(int i = 1; i < N*N; i++) {
                
                int ny = y + dy[direction];
                int nx = x + dx[direction];
                
                // 만약 범위에 없거나, 이미 방문한 케이스면
                if(!isIn(N, ny, nx) || visited[ny][nx] == true) {
                    direction = (direction+1) % 4; // 방향 변경
                    ny = y + dy[direction];
                    nx = x + dx[direction];
                }
                
                if(visited[ny][nx] == false) { // 탐색 가능하고 방문을 안했으면
                    arr[ny][nx] = i+1;
                    visited[ny][nx] = true; // 방문처리
                    y = ny; // y, x도 지속적으로 변경해준다.
                    x = nx; 
                }

            }
            
            // 배열 출력
            System.out.printf("#%d%n", test_case);
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    System.out.printf("%d ", arr[i][j]);
                }
                System.out.println();
            }
            
		}
	}
	
	// 유효한 배열 인덱스 체킹 용도
	static boolean isIn(int N, int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < N;
	}
}