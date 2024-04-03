import java.io.*;
import java.util.*;

// Upper Bound = Long
// 1. pq = 1000000 * 15 * 1000 =  150억
// 2. result = 150억 * 1000 = 15조
public class Main{
    static int N, M;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    static long result;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// N개의 카드 입력받기 O(NlogN)
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    // 우선순위큐를 사용해서 정렬하기.
		    pq.offer(Long.parseLong(st.nextToken()));
		}
		
		// M번의 횟수만큼 우선순위큐의 앞의 두 카드를 빼서 갱신해주기(O(MlogM))
		for(int i = 0; i < M; i++) {
		    long tmp1 = pq.poll();
		    long tmp2 = pq.poll();
		    long sum = tmp1 + tmp2;
		    
		    // 덮어쓰는 행위
		    pq.offer(sum);
		    pq.offer(sum);
		    
		}
		
		// pq에 있는 값을 모두 더함(O(N))
		for(long num: pq) {
		    result += num;
		}
		
		System.out.println(result);
		
	}
}
