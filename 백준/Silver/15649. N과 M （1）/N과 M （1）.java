import java.io.*;
import java.util.*;

public class Main
{
    static int N, M; //nPm
    static int[] number;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		number = new int[M];
		visited = new boolean[N+1];
		permutation(0);
		
		bw.write(sb.toString());
		bw.close();
		
	}
	
	static void permutation(int depth) {
	    if(depth == M) {
	        for(int i = 0; i < M; i++) {
	            sb.append(number[i]).append(" ");
	        }
	        sb.append('\n');
	        return;
	    }
	    
	    for(int i = 1; i <= N; i++) {
	        if(visited[i]) continue;
	        visited[i] = true;
	        number[depth] = i;
	        permutation(depth+1);
	        visited[i] = false;
	    }
	}
}
