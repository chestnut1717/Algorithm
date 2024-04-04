import java.io.*;
import java.util.*;

public class Main {
    static final int N = 10;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int map[][] = new int[N][N];
    static boolean flag; // 한번이라도 올바르게 다 뒤집었는가
    static int min = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i  =0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// 브루트포스?
		// 시간복잡도 => 100 * 25 * 10 * 6 * 4 * 25 = 1500만
        
        dfs(0, 0, 0); // dfs를 한다.
        if(min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
	}
	
	static void dfs(int y, int x, int cnt) {
	    // y가 10이 되었을 때!
        if (y >= 9 && x > 9) {
            min = Math.min(min, cnt);
            return;
        }
	    
	    // backtracking
	    if(min <= cnt) {
	        return;
	    }
	    
	    // 아래쪽으로 이동
	    if(x >= N) {
	        dfs(y+1, 0, cnt);
	        return;
	    }
	    
	    // 이제 n값을 하나씩 준다!
	    if(map[y][x] == 1) {
    	    for(int n = 5; n >= 1; n--) {
                if (paper[n] > 0 && isAttach(y, x, n)) {
                    attach(y, x, n, 0); // 색종이를 붙임.
                    paper[n]--;
                    dfs(y, x + 1, cnt + 1);
                    attach(y, x, n, 1); // 색종이를 다시 뗌.
                    paper[n]++;
                }
    	    }
    	    // map이 i가 아닐 경우 걍 넘긴다.
	    } else {
    	    dfs(y, x+1, cnt);
    	}

	    
	}
	





	    // 색종이를 붙이는 함수.
    public static void attach(int y, int x, int size, int state) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                map[i][j] = state;
            }
        }
    }
    	

    // 색종이를 붙일 수 있는지 확인.
    public static boolean isAttach(int y, int x, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (i < 0 || i >= N || j < 0 || j >= N) {
                    return false;
                }

                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
	

}