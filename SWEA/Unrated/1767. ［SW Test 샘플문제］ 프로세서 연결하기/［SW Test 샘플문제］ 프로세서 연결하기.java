import java.util.*;
import java.io.*;

public class Solution

{
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {-1, 0, 1, 0};
    static int N;
    static int[][] arr, arrCopy;
    static int cellCnt;
    static int cnt;
    static List<Pair> lst;
    static int finalResult;
    static int maxDepth;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
		    
		    N = Integer.parseInt(br.readLine().trim());
		    arr = new int[N][N];
		    arrCopy = new int[N][N];
		    cellCnt = 0;
		    cnt = 0; // 전선 연결 완료된 코어
		    lst= new ArrayList<>();
		    maxDepth = 0;
		    finalResult = Integer.MAX_VALUE;
		    
		    for(int i = 0; i < N; i++) {
		        st = new StringTokenizer(br.readLine().trim());
		        for(int j = 0; j < N; j++) {
		            arr[i][j] = Integer.parseInt(st.nextToken());
		            if(arr[i][j] == 1 && (i == 0 || j == 0 || i == N-1 || j == N-1)) {
		                cellCnt++;
		                cnt++;
		            }
		            // 우선 가장자리가 아닌 값들을 모두 큐에 넣어줌
		            else if(arr[i][j] == 1) {
		                cellCnt++;
		                lst.add(new Pair(i, j));
		            }
		        }
		            
		    }

		    for(int i = 0; i < N; i++) {
		        for(int j = 0; j < N; j++) {
		            arrCopy[i][j] = arr[i][j];
		        }
		    }
		  //  System.out.println(cellCnt);
		    dfs(0, cnt, 0);
		    
		    sb.append("#").append(test_case).append(" ");
		    sb.append(finalResult).append('\n');
		    
		}
		
		bw.write(sb.toString());
		bw.close();
		    


	}
	
    static void dfs(int start, int count, int lineCount) {
        // 전체 코어를 다 체크했을 때
        if (start == lst.size()) {
            // 코어 개수가 기존보다 많거나, 같으면서 연결된 전선 길이가 더 짧을 때
            if (count > maxDepth || (count == maxDepth && lineCount < finalResult)) {
                maxDepth = count;
                finalResult = lineCount;
            }
            return;
        }
	    
	    Pair p = lst.get(start);
	    // 4방향 탐색
	    for(int i = 0; i < 4; i++) {
	        int coloredLine = 0;
	        if(isValid(p, i)) {
    	        // array색칠해주고
    	        coloredLine = color(p, i);
    	        dfs(start+1, count+1, lineCount + coloredLine);
    	        restore(p, i);
	        } else dfs(start+1, count, lineCount);

	    }
	    
	}


	
	static boolean isValid(Pair p, int direction) {
	    int startY = p.y;
	    int startX = p.x;

        int ny = startY + dy[direction];
        int nx = startX + dx[direction];
        while(isIn(ny, nx)) {
            if(arr[ny][nx] != 0) return false; // 빈 칸이 아닌 경우
            ny += dy[direction];
            nx += dx[direction];
        }
        return true;

	}
	static void restore(Pair p, int direction) {
	    int colCount = 0;
	    int startY = p.y;
	    int startX = p.x;

        int ny = startY + dy[direction];
        int nx = startX + dx[direction];
        while(isIn(ny, nx)) {
            arr[ny][nx] = 0;
            colCount++;
            ny += dy[direction];
            nx += dx[direction];
        }
	}
	
	static int color(Pair p, int direction) {
	    int colCount = 0;
	    int startY = p.y;
	    int startX = p.x;

        int ny = startY + dy[direction];
        int nx = startX + dx[direction];
        while(isIn(ny, nx)) {
            arr[ny][nx] = -1;
            colCount++;
            ny += dy[direction];
            nx += dx[direction];
        }
        return colCount;
	}
	
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < N;
	}
}


class Pair {
    int y, x;
    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

// 우선  ㅇ가장자리 아닌 것들만 대상으로 탐색한다.
// dfs 종료조건 => 큐가 비었을 경우, 최솟값 갱신
// 하나의코어를 대상으로
//// 만약 해당 코어가 valid하면 => 큐에서 하나 빼주고, 값 더해주고 dfs로 넘어감
//// 만약 해당 코어가 valid X => 큐에서 하나 빼주고 dfs로ㄴ ㅓㅁ어감
