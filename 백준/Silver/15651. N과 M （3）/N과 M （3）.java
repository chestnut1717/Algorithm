import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int M;
    static int[] input;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		numbers = new int[M];
		for(int i = 1; i <= N; i++) {
		    input[i-1] = i;
		}
		
		perm(0);
		
		bw.write(sb.toString());
		bw.close();
	}
	
	// 중복순열 구하기
	static void perm(int cnt){
	    if(cnt == M) {
	        for(int i = 0; i < M; i++){
	            sb.append(numbers[i]).append(" ");
	        }
	        sb.append('\n');
	        return;
	    }
	    for(int i = 0; i < N; i++){
	        numbers[cnt] = input[i];
	        perm(cnt + 1);
	    }
	} 
}
