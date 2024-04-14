import java.util.*;
import java.io.*;

public class Main
{
    // 중간값 ~ 최댓값을 저장하는 MinHeap
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);
    // 최솟값 ~ 중간값을 저장하는 MaxHeap
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
		    int num = Integer.parseInt(br.readLine());
		 
		    // 만약 두 개의 size가 같으면 => maxHeap에 저장(왜냐하면 짝수개 => 더 작은 수를 출력)
		    if(minHeap.size() == maxHeap.size()) maxHeap.offer(num);
		    else minHeap.offer(num);
		    
		    // 그리고 둘 다 비지 않았고, maxHeap의 중간값이 minHeap의 중간값보다 더 크면 처리
		    if(!minHeap.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
		        // swap
		        int tmp = minHeap.poll();
		        minHeap.offer(maxHeap.poll());
		        maxHeap.offer(tmp);
		    }
		    // 그리고 항상 maxHeap에 있는 것을 ㅃ보아줌
		    sb.append(maxHeap.peek()).append('\n');
		}
		

		bw.write(sb.toString());
		bw.close();
	}
}
