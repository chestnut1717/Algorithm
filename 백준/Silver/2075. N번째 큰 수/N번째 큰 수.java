import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        pq.offer(Integer.parseInt(st.nextToken()));
		    }
		    if(pq.size() > N) {
    		    for(int j = 0; j < N; j++) {
    		        pq.poll();
    		    }
		    }
		}
		
		// 반복문 다 끝냈으면
		System.out.println(pq.poll());
	}
}