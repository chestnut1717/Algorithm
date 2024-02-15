import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static PriorityQueue<Integer> q= new PriorityQueue<>();
    static int result;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    for(int i = 0; i < N; i++) {
	        q.offer(Integer.parseInt(br.readLine()));
	    }

		
		// 큐에 들어오는 값 빼내면서 더하기
		while(q.size() > 1){
		    int a = q.poll();
		    int b = q.poll();
		    result += a + b;
		    q.offer(a+b);
   
		}
		System.out.println(result);
	}
}
