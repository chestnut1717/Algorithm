import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 1159 요세푸스 문제
 * - 소요시간 : 15분 
 * - 난이도 : 하
 * - 아이디어 : 큐를 활용해서 구현하면 된다. 
 * - 발전 방향 : 원형 큐 공식을 활용해 배열로 구현할 수도 있다.
 */
public class Main {
    static int N;
    static int K;
    static Queue<Integer> q= new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
	    // Queue에 1 ~ N까지 데이터 넣는다.
		for(int i = 1; i <= N; i++) {
		    q.offer(i);
		}
		
		sb.append("<");
	    
	    // 마지막 반점때문에 size가 1이 될 때까지
		while(q.size() > 1){
		    // K-1번째는 다시 넣어줌
		   for(int i = 0; i < K-1; i++) {
		       	q.offer(q.poll());
		   }

		   // K번째에 값은 아예 빼줌
		   sb.append(q.poll()).append(", ");
		}
	    sb.append(q.poll()).append(">");
		
		
		bw.write(sb.toString());
		bw.flush();
		
	}
}