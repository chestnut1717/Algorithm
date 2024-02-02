import java.util.*;
import java.io.*;

/**
 * @author 박선홍
 * @since jdk1.8
 * - 문제 : BJ 2164 카드2
 * - 소요시간 : 5분 
 * - 난이도 : 쉬움
 * - 큐를 활용해서 구하면 된다.
 * 개선점 : 공식이 존재 => O(nlogn)으로도 풀 수 있음
 */
public class Main
{
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 입력 받음
		Queue<Integer> q = new ArrayDeque<>(); // 큐 생성
		for(int i = 1; i <= N; i++) {
		    q.add(i);
		}
		
		// 큐 탐색
		int tmpVal;
	    while(q.size() > 1) { // 큐의 원소가 하나가 될 때까지
	        q.poll(); // 우선 맨 앞 하나 없애주고
	        tmpVal = q.poll(); // 다시 뽑고
	        q.offer(tmpVal); // 맨 뒤로 push
	    }
	    
	    System.out.println(q.peek()); // 가장 마지막에 남은 카드 1장 출력
	}
}
