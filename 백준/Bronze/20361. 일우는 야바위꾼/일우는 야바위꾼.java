import java.util.*;
import java.io.*;

public class Main {
	static int T; // 테스트 케이스
	static int N, X, K;
	static boolean[] cups;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cups = new boolean[N+1]; // 컵 개수 + 1
		cups[X] = true; // 간식 위치 true
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			swap(A, B);
		}
		int result = find();
		sb.append(result);
			
		
		
		bw.write(sb.toString());
		bw.close();
		

	}
	static void swap(int A, int B) {
		boolean tmp = cups[A];
		cups[A] = cups[B];
		cups[B] = tmp;
	}
	static int find() {
		for(int i = 0; i < N+1; i++) {
			if(cups[i] == true) return i;
		}
		return -1;
	}

}
