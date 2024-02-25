import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int M;
    static int[] input;
    static int[] numbers;
    static boolean[] visited;
    static LinkedHashSet<List<Integer>> set = new LinkedHashSet<>();
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		visited = new boolean[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    input[i] = Integer.parseInt(st.nextToken());
		}

		
		Arrays.sort(input);
		
		perm(0);
		for(List<Integer> lst: set) {
		    for(int i: lst) {
		        sb.append(i).append(" ");
		    }
		    sb.append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}
	
	// 순열 구하기
    static void perm(int cnt) {
        if(cnt == M) {
            List<Integer> tmpList = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                tmpList.add(numbers[i]);
            }
            set.add(tmpList);
            return;
        }
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            numbers[cnt] = input[i];
            perm(cnt+1);
            visited[i] = false;
        }
    }
	    
}
