import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 11286 절댓값 힙
 * - 소요시간 : 35분 
 * - 난이도 : 중
 * - 메모리 : 51232 KB / 시간 : 1536 ms
 * - 아이디어 : 들어올 수 있는 연산의 개수가 100,000개이므로, 조건에 맞는 최솟값을 찾기 위해서는 우선순위 큐 사용
 * - 우선순위 큐에서 우선순위를 직접 Comparator을 구현
 */
public class Main{
    static int N; // 연산의 개수
    static PriorityQueue<Integer> pq;
	public static void main(String[] args)throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuilder sb = new StringBuilder(); 
	   
	    N = Integer.parseInt(br.readLine());
	    
	    pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
	        @Override
	        public int compare(Integer i1, Integer i2) {
	            if (Math.abs(i1) == Math.abs(i2)) { // 절댓값이 같은 경우
	                return i1 - i2; 
	            } else { // 그렇지 않는 경우
	                return Math.abs(i1) - Math.abs(i2);
	            }
	        }
	    });
	    
	    // 숫자를 입력받음
	    for(int i = 0; i < N; i++) {
	        int tmp = Integer.parseInt(br.readLine());
	        // 배열의 크기가  0인 경우
	        if (pq.size() == 0 && tmp == 0) {
	            sb.append(0).append('\n');
	            continue;
	        }
	        // 숫자가 0인경우
	        if (tmp == 0) {
	            sb.append(pq.poll()).append('\n'); // 루트 값을 우선순위 큐에서 뺀 다음 출력
	        }
	        else {
	            pq.offer(tmp);
	        }
	    }
	    
	   
	    bw.write(sb.toString());
	    bw.close();
	    
		
	}
}
