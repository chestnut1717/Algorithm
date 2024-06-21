import java.util.*;
import java.io.*;

public class Main
{
    static int M, N;
    static int[][] arr;
    static int result = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		
		arr = new int[M][N];
		for(int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		// 그리고 모든 arr에 대해 탐색하는데
		// 각각의 point마다 1*1, 2*2 이렇게 탐색하는 방식으로 진행한다.
		// 그리고 이전 값의 최댓값부터 다음 L 탐색한다.(3*3이 최댓값이면 다음도 3부터 시작)
		
		int startY = 0;
		int startX = 0;
		boolean checked = false;
		while(startY < M && startX < N) {
		    // 만약 범위를 초과할 일이 생기는 경우
		    if(startX + result >= N) {
		        startX = 0;
		        startY++;
		        checked = false;
		        continue;
		    }
		    if(startY + result >= M) {
		        checked=false;
		        break; // 아예 종료
		    }
		  
		    if(isSquare(startY, startX, result+1, checked)) {       
		        result++;
		        checked=true;
		        continue; // 다시 result+1 길이의 정사각형 배열 탐색해야 하므로
		    } else {
		        startX++;
		        if(startX == N) {
		            startX = 0;
		            startY++;
		        }
		        checked = false;
		    }
		    
		}
		System.out.println(result);
	}
	static boolean isSquare(int y, int x, int L, boolean checked) {
	   // // y, x지점에서 checked된 경우
	    if(!checked) {
            for(int i = y; i < y+L; i++) {
    	       for(int j = x; j < x+L; j++) {
    	           if(arr[i][j] != 0) return false;
    	       }
    	    }
	    } else {
	    // 가로 탐색
    	    for(int i = x; i < x+L; i++) {
    	        if(arr[y+L-1][i] != 0) return false;
    	    }
    	    for(int i = y; i < y+L; i++) {
    	        if(arr[i][x+L-1] != 0) return false;
    	    }
	    }

	    return true;
	}
}