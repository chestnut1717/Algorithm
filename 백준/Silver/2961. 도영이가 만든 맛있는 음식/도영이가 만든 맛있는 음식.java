import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] sour;
    static int[] bitter;
    static boolean[] visited;
    static int taste = Integer.MAX_VALUE; // 최솟값을 구해야 하므로 최댓값을 만들어서 구한다.
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    N = Integer.parseInt(br.readLine()); // 재료의 개수 입력;
	    
	    sour = new int[N];
	    bitter = new int[N];
	    visited = new boolean[N];
	    
	    // 음식의 신맛 ,쓴맛 입력
	    for(int i = 0; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        sour[i] = Integer.parseInt(st.nextToken());
	        bitter[i] = Integer.parseInt(st.nextToken());
	    }
	    search(0, 1, 0, 0);
	    System.out.println(taste);
	    
	    
	    
	}
	
	// 모든 부분수열 계산하는 코드
	static void search(int cnt, int tmp_s, int tmp_b, int size) {
	    // 종료조건
	    if (cnt == N) {
	        if (size > 0) {
	           int tmp_taste = Math.abs(tmp_s - tmp_b);
        	   taste = Math.min(taste, tmp_taste); 
	        }
	        return;
	    }
        
        visited[cnt] = true;
	    search(cnt+1, tmp_s * sour[cnt], tmp_b + bitter[cnt], size+1);
	    visited[cnt] = false;
	    search(cnt+1, tmp_s, tmp_b, size);

	    
	}
}
