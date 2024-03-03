import java.util.*;
import java.io.*;

class Solution
{
    static final int MAX_GRID = 1000;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int[][] map;
    static int[][] status; // 배양 세포 상태 나타내는 것 (0: 존재 X, 1: 비활성화, 2: 활성화, 3: 임시 찜, -1 : 죽음)
    static int N, M, K; // 줄기세포 분포된 영역의 넓이!줄기세포 배양 시간
    static List<Pair> cellList;
    static HashSet<Pair> set; // 중복처리하는 집합
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    // N, M, K 입력
		    N = Integer.parseInt(st.nextToken());
		    M = Integer.parseInt(st.nextToken());
		    K = Integer.parseInt(st.nextToken());
		    
		    // N * M 그리드 정보 주어지기
		    map = new int[MAX_GRID][MAX_GRID];
		    status = new int[MAX_GRID][MAX_GRID];
		    cellList = new LinkedList<>();
		    for(int y = 0; y < N; y++) {
		        int gridY = y + MAX_GRID/2;
		        st = new StringTokenizer(br.readLine());
		        for(int x = 0; x < M; x++) {
		            
		            int gridX = x + MAX_GRID/2;
		            map[gridY][gridX] = Integer.parseInt(st.nextToken());
		            if(map[gridY][gridX] >= 1) {
		                status[gridY][gridX] = 1; // 존재는 함
		                cellList.add(new Pair(gridY, gridX, map[gridY][gridX] ));
		            }
		            
		        }
		    }
		    
		    // t시간 후 track
		    for(int time = 1; time <= K; time++) {
		        // 모든 저장되어 있는 cell에 대해
		        
		        set = new HashSet<>(); // 매 시간마다 초기화
		        List<Pair> tmpTrash = new ArrayList<>();
		        for(Pair cell: cellList) {
		            // 비활성화 상태일 때
		            if(status[cell.y][cell.x] == 1) {
		                if(cell.time < cell.num) cell.time++;
		                else if (cell.time == cell.num) activate(cell);
		            }
		            // 활성화 상태일 때
		            else if (status[cell.y][cell.x] == 2) {
		                // 활성화가 된 직후 한 번만 퍼트리면 되므로, num과 cell.time이 값이 동일할 때 퍼트림
		                if(cell.time == cell.num) {
		                    spread(cell);
		                }
		                cell.time--; // 하나씩 유지시간 줄어듦
		                // 만약 해당 시간이 모두 줄어들었을 경우
		                if(cell.time == 0) {
		                    status[cell.y][cell.x] = -1;
		                    tmpTrash.add(cell);
		                }
		                
		            }
		            
		        }
		        // 그리고 변경된 값들은 set에서 모두 처리
		        for(Pair cell: set) {
		            status[cell.y][cell.x] = 1; // 3 -> 1로 바꿈
		            cellList.add(cell); // cellList에 더해줌!
		        }
		        // 지울 거리들은 여기서 지움
		        for(Pair cell: tmpTrash) {
		            cellList.remove(cell);
		        }

		        
		        
		    }
		    
            // 남아 있는 것들 세기
		    int result = cellList.size();
		    sb.append("#").append(test_case).append(" ").append(result).append('\n');
		    

		}
		
		// 모든 테스트 끝나고 최종 답 출력
		bw.write(sb.toString());
		bw.close();
	}
	
	// 퍼트리는 함수
	static void spread(Pair cell) {
	    // 4방탐색
	    for(int i = 0; i < 4; i++) {
	        int ny = cell.y + dy[i];
	        int nx = cell.x + dx[i];
	        // 만약 해당 세포에 줄기세포가 존재하지 않으면 
	        // 이미 존재하거나 죽은 상태이면 무시
	        if(status[ny][nx] == 2 || status[ny][nx] == 1 || status[ny][nx] == -1) {
	            continue;
	        }
	        // 만약에 아직 아무것도 없을 경우
	        else if(status[ny][nx] == 0) {
	            map[ny][nx] = cell.num;
	            status[ny][nx] = 3;
	            set.add(new Pair(ny, nx,cell.num));
	        }
	        // 누가 찜해놨을 경우
	        else if(status[ny][nx] == 3) {
	            map[ny][nx] = Math.max(map[ny][nx], cell.num);
	        }
	    }
	}
	
	
	// 활성화시키는 함수
	static void activate(Pair cell) {
        status[cell.y][cell.x] = 2; // 1 -> 2 활성화 상태로 변경
	}
	
	static class Pair{
	    int y, x, num, time;
	    public Pair(int y, int x, int num) {
	        this.y = y;
	        this.x = x;
	        this.num = num; // 생명력
	        this.time = 1; // 활성시간 될 때까지 기다림
	    }
	}
}