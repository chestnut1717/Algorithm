import java.util.*;
import java.io.*;

public class Main
{
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int N, M, H;
    static int[][] arr;
    static Pair home;
    static List<Pair> milks;
    static int result;
    
	public static void main(String[] args) throws Exception{
	    // 핵심 : 많은 우유를 먹고 집으로 돌아와야 함
	    // DFS탐색을 통해 가능한 경우의 수를 탐색하고 => 가지 못하는 경우가 있으면 백트래킹으로 조지자
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		milks = new ArrayList<>();
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		        if (arr[i][j] == 1) home = new Pair(i, j);
		        if (arr[i][j] == 2) milks.add(new Pair(i, j));
		    }
		}
		
		// 그리고 milks를 가지고 재귀 탐색을 한다.
		dfs(home.y, home.x, 0, M);
		System.out.println(result);
		
		
	}
	
	static void dfs(int y, int x, int cnt, int hp) {
	    // 최댓값을 이미 갱신했을 경우
	   if(result == milks.size()) return;
	   // 만약 집까지 현재 체력으로 갈 수 있다면 우선 값 갱신해보자!
	   if(calcDist(y, home.y, x, home.x) <= hp) {
	       result = Math.max(cnt, result);
	   }
	    
	    // 온 milk를 탐색한다.
	   for(int i = 0; i < milks.size(); i++) {
	        Pair milk = milks.get(i);
	  
	        // 갈 수 있는 에너지 계산
	        int energy = hp - calcDist(y, milk.y, x, milk.x);
	        // 만약 우유를 먹고도 걸을 수 있다면, 그리고 해당 우유가 이미 방문한 곳이 아니라면
	        if(energy >= 0 && arr[milk.y][milk.x] == 2) {
	            arr[milk.y][milk.x] = 0;
	            // 방문
	            dfs(milk.y, milk.x, cnt+1, energy + H);
	            // 원복
	            arr[milk.y][milk.x] = 2;
	        }
	    }
	    
	}
	
    static int calcDist(int sy, int ey, int sx, int ex) {
        return (int) (Math.abs(ey - sy) + Math.abs(ex - sx));
    }
	
	static class Pair {
	    int y, x;
	    public Pair(int y, int x) {
	        this.y = y;
	        this.x = x;
	    }
	}
}