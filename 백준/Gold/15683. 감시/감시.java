import java.util.*;
import java.io.*;

public class Main {
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int N, M;
    static int[][] arr;
    static int[][] track;
    static int cnt; // cctv 개수
    static int[] possible; // 회전 경우의 수 담는 배열
    static List<List<Integer>> possibleLst = new ArrayList<>();
    static List<Pair> cctv = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
	    track = new int[N][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		        if(1 <= arr[i][j] && arr[i][j] <= 5) {
		            cctv.add(new Pair(i, j)); // cctv만 따로 저장
		            cnt++;
		        }
		    }
		}
		
		possible = new int[cnt];
		// copy
		for(int i = 0; i < N; i++) {
		    for(int j = 0; j < M; j++) {
		        track[i][j] = arr[i][j];
		    }
		}
		

		// 중복순열 구하기
		perm(0);
		
		// 모든 회전하는 경우의 수에 대해서 구하기
		for(List<Integer> lst: possibleLst) {
		    // 모든 direction에 대해 구하기
		    for(int i = 0; i < lst.size(); i++) {
		      //  System.out.println(lst.toString());
		        Pair nowCCTV = cctv.get(i); // 현재 cctv
		        int direction = lst.get(i); // direction
		        rotate(nowCCTV, direction);
		        // 0의 갯수 구하기

		      //  result = Math.min(count(), result);
		      //  print();
                
                // System.out.println();
 		    }
 		 //   print();
 		 //   System.out.printf("%d, %d%n", count(), result);
 		    
 		    result = Math.min(count(), result);
 		    copy();
 		    
		}
		
		
		System.out.println(result);
        
		
        
		

	}
	
	static int count() {
	    int zeroCnt = 0;
        for(int j = 0; j < N; j++) {
            for(int k = 0; k < M; k++) {
                if(track[j][k] == 0) zeroCnt++;
            }
        }
        return zeroCnt;
	}
	
	static void print() {
	   for(int j = 0; j < N; j++) {
    	   for(int k = 0; k < M; k++){
    	       System.out.printf("%d", track[j][k]);
    	        }
            System.out.println();
        }
	}
	static void copy() {
	    for(int j = 0; j < N; j++) {
            for(int k = 0; k < M; k++) {
        		track[j][k] = arr[j][k];
        	}
        }
	}
	static boolean isIn(int y, int x) {
	    return 0 <= y && y < N && 0 <= x && x < M;
	}
	static void rotate(Pair nowCCTV, int direction) {
	    int y = nowCCTV.y;
	    int x = nowCCTV.x;
	    
	    if (arr[y][x] == 1) {
            dfs(y, x, direction % 4);
	    }
	    else if (arr[y][x] == 2) {
	        dfs(y, x, direction % 4);
	        dfs(y, x, (direction + 2) % 4);
	    }
	    else if (arr[y][x] == 3) {
	        dfs(y, x, direction % 4);
	        dfs(y, x, (direction+3) % 4);
	    }
	    else if (arr[y][x] == 4) {
	        dfs(y, x, (direction) % 4);
	        dfs(y, x, (direction+2) % 4);
	        dfs(y, x, (direction+3) % 4);
	    }
	    else if (arr[y][x] == 5) {
	        dfs(y, x, (direction) % 4);
	        dfs(y, x, (direction+1) % 4);
	        dfs(y, x, (direction+2) % 4);
	        dfs(y, x, (direction+3) % 4);
	    }
	    
	}
	
	static void dfs(int y, int x, int direction) {
	    if(!isIn(y, x) || arr[y][x] == 6) {
	        return; // 종료 조건
	    }
	    track[y][x] = 7; // #표시
	    int ny = y + dy[direction];
	    int nx = x + dx[direction];
	    dfs(ny, nx, direction);
	}
	
	// 중복순열
	static void perm(int depth) {
	    if(depth == cnt) {
	       List<Integer> tmp = new ArrayList<>();
	       for(int i = 0; i < possible.length; i++) {
	           tmp.add(possible[i]);
	       }
	       possibleLst.add(tmp);
	       return;
	    }
	    for(int i = 1; i <= 4; i++) {
	        possible[depth] = i;
	        perm(depth+1);
	    }
	    
	}
	

	static class Pair {
    	    int y;
    	    int x;
    	    public Pair(int y, int x) {
    	        this.y = y;
    	        this.x = x;
    	    }
	}
}
