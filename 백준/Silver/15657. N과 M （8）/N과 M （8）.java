import java.io.*;
import java.util.*;

public class Main
{
    static int N, M;
    static int[] arr;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// N개의 숫자 입력받음
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		// 배열 초기화
		numbers = new int[M];
		calc(0, 0);
		
		bw.write(sb.toString());
		bw.close();
		
	}

	static void calc(int depth, int prev) {
	    if(depth == M) {
	        for(int i = 0; i < M; i++) {
	            sb.append(numbers[i]).append(' ');
	        }
	        sb.append('\n');
	        return;
	    }
	    for(int i = prev; i < N; i++) {
	        numbers[depth] = arr[i];
	        calc(depth+1, i);
	    }
	}
}