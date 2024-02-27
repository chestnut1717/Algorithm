import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] memo;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		memo = new int[1000004]; // 1~N까지 다루는 변수 초기화
		memo[1] = 0;
		memo[2] = 1;
		memo[3] = 1;
		memo[4] = 2;
		memo[5] = 3;
	    if(N <= 5) {
	        sb.append(memo[N]);
	    }
	    else {
	        for(int i = 4; i <= N; i++) {
	            if(i % 6 == 0) memo[i] = Math.min(Math.min(memo[i/3], memo[i/2]), memo[i-1]) + 1;
	            else if(i % 2 == 0) memo[i] = Math.min(memo[i/2], memo[i-1]) + 1;
	            else if(i % 3 == 0) memo[i] = Math.min(memo[i/3], memo[i-1]) + 1;
	            else memo[i] = memo[i-1]+1;
	        }

	        sb.append(memo[N]);
	   }
	    
		
		bw.write(sb.toString());
		bw.close();
	}
}
