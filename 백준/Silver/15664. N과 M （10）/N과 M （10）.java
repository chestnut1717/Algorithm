import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    static int[] arr;
    static int[] num;
    
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// N개의 수 입력
		arr = new int[N];
		num = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		combi(0, 0);
		bw.write(sb.toString());
		bw.close();
		
	}
	
	static void combi(int depth, int start) {
	    if(depth == M) {
            for(int i = 0; i < M; i++) {
	            sb.append(num[i]).append(' ');
	        }
	        sb.append('\n');

	        return;
	    }
	    int before = -1; // 이전과 같은 값인지 중복 체크해주는 역할
	    for(int i = start; i < N; i++) {
	        if(before == arr[i]) continue;
	        before = arr[i]; // 이전 값과 다르니깐 before 갱신
	        num[depth] = arr[i];
	        combi(depth+1, i+1);

	    }
	}
}
