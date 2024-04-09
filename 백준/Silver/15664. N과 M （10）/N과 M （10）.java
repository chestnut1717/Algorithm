import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    // static boolean[] visited = new boolean[10001];
    static int[] arr;
    static int[] num;
    static boolean[] visited;
    static HashSet<List<Integer>> set = new HashSet<>();
    
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
		visited = new boolean[N];
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
	        List<Integer> tmp = new ArrayList<>();
	        for(int i = 0; i < M; i++) {
	            tmp.add(num[i]);
	        }
	        if(!set.contains(tmp)) {
	            set.add(tmp);
	            for(int i = 0; i < M; i++) {
    	            sb.append(num[i]).append(' ');
    	        }
    	        sb.append('\n');
	        }

	        return;
	    }
	    for(int i = start; i < N; i++) {
	        if(visited[i]) continue;
	        visited[i] = true;
	        num[depth] = arr[i];
	        combi(depth+1, i+1);
	        visited[i] = false;
	    }
	}
}
