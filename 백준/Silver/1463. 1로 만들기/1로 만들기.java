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
		
		memo = new int[N+1]; // 1~N까지 다루는 변수 초기화
		

        for(int i = 2; i <= N; i++) {
            memo[i] = memo[i-1]+1;
            if(i % 2 == 0 && memo[i] > memo[i/2] + 1) memo[i] = memo[i/2] + 1;
            if(i % 3 == 0 && memo[i] > memo[i/3] + 1) memo[i] = memo[i/3] + 1;
        }

        sb.append(memo[N]);
	   
	    
		
		bw.write(sb.toString());
		bw.close();
	}
}
