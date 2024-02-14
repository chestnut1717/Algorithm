import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 1992 쿼드트리
 * - 소요시간 : 40분 
 * - 난이도 : 중
 * - 아이디어 : 압축할 구간을 4분할로 나눠서, isAllSame()함수를 통해 해당 구간의 원소가 모두 동일하면 압축
 * - 그렇지 않으면 계속 탐색 (종료조건 나타날 때까지)
 */
public class Main {
    static int N;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		// 배열 입력
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
		    char[] charArr = br.readLine().toCharArray();
		    for(int j = 0; j < N; j++) {
		        arr[i][j] = charArr[j] - '0'; // char to int
		    }
		}
		
		divide(0, 0, N);
		bw.write(sb.toString());
		bw.close();
	}
	static void divide(int y, int x, int n) {
	    
	    if(n == 1) { // 1*1일 때
	        sb.append(arr[y][x]);
	        return;
	    }
	    
	    // 모두 0 아니면 1로 압축가능한지 check
	    int result = isAllSame(y, x, n);
	    
	    if (result == 1) {
	        sb.append(1);
	        return;
	    }
	    else if (result == 0) {
	        sb.append(0);
	        return;
	    }
	    else {
	        sb.append("(");
	        int half = n*1/2;
	        divide(y, x, half);
	        divide(y, x+half, half);
	        divide(y+half, x, half);
	        divide(y+half, x+half, half);
	        sb.append(")");
	    }
	    
	}
	// 해당 사분면의 원소가 모두 동일한지 체크
	static int isAllSame(int y, int x, int n) {
	    int init = arr[y][x];
	    for(int i = y; i < y + n; i++) {
	        for(int j = x; j < x + n; j++) {
	            if(init != arr[i][j]) return -1;
	        }
	    }
	    return init;
	    
	}
}
