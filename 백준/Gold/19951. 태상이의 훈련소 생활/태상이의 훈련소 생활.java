import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    static int[] ground;
    static int[] cumsum;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuilder sb = new StringBuilder();
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 연병장 초기화
		ground = new int[N+1]; // imos쓰려면 N+1까지 초기화 및 1부터 시작
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
		    ground[i] = Integer.parseInt(st.nextToken());
		}
		
		cumsum = new int[N+2];
		
		// M명의 조교들의 order
		for(int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    	    int end = Integer.parseInt(st.nextToken());
    	    int goal = Integer.parseInt(st.nextToken());
    	    
    	    cumsum[start] += goal;
    	    cumsum[end+1] += (-goal);
		}
		
        // imos알고리즘 방법
		// 이제 누적합 처리 해준다.
		for(int i = 1; i <= N; i++) {
		    cumsum[i] += cumsum[i-1];
		}
		
		
		// 이제 합을 해준다.
		for(int i = 1; i <= N; i++) {
		    ground[i] += cumsum[i];
		    sb.append(ground[i]).append(' ');
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
}