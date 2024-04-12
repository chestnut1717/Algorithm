import java.io.*;
import java.util.*;

public class Main
{
    static int N, M;
    static int[] numbers;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 초기화
		numbers = new int[M];
		perm(0);
		
		bw.write(sb.toString());
		bw.close();
		
		
	}

	// 중복순열
	static void perm(int depth) {
	    if(depth == M) {
	        for(int i = 0; i < M; i++) {
	            sb.append(numbers[i]).append(' ');
	        }
	        sb.append('\n');
	        return;
	    }
	    for(int i = 1; i <= N; i++) {
	        numbers[depth] = i;
	        perm(depth+1);
	    }
	}
}
