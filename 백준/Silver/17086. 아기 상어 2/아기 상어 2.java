import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean [][] visited;
    static List<Pair> coor = new ArrayList<>();
    static List<Pair> shark = new ArrayList<>();
    static int result = Integer.MIN_VALUE;
    
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < M; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		        if(arr[i][j] == 1) shark.add(new Pair(i, j));
		        else coor.add(new Pair(i, j));
		    }
		}
		
		// 완탐으로 풀기
		for(Pair p: coor) {
		    int safe = Integer.MAX_VALUE; // 안전거리 : 상어와 가장 가까운거리
		    for(Pair sk: shark) {
		        int tmpSafe = calc(p.y, p.x, sk.y, sk.x); 
		        safe = Math.min(safe, tmpSafe); // 상어와 가장 거리가 가까운 값 선택
		    }
		    
		    result = Math.max(result, safe);
		}
		
		System.out.println(result);
	
	}
	// 상어와 현재 위치 거리 계산
    static int calc(int ny, int nx, int y, int x) {
        return Math.max( Math.abs(ny - y), Math.abs(nx - x));
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