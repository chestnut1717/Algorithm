import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        //우선순위 큐를 선언합니다.
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
        //주어진 정숫 N만큼 카드묶음별 카드 갯수를 입력받습니다.
		
		int total = 0;
        //total : 구하고자 하는 '총 비교횟수'입니다.
		
		while(pq.size()>1) {
        //우선순위 큐의 원소가 하나만 남았을 때(더 이상 더할 쌍이 남지 않았을 때) 종료합니다.
			int a = pq.poll();
			int b = pq.poll();
			
			int comparison = a+b;
            //각 단계별 비교횟수이자, 비교한 두 카드묶음을 합친 갯수입니다.
			
			total += comparison; // total을 이번 단계의 비교횟수만큼 증가시킵니다.
			pq.add(comparison); // 이번 단계에 합쳐서 만들어진 카드묶음을 다시 우선순위 큐에 넣습니다.
			
		}
		
		System.out.println(total);
		
		br.close();
	}
}