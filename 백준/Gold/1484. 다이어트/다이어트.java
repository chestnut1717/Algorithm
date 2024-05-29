import java.io.*;
import java.util.*;

public class Main
{
    static int G; // 주어지는 몸무게
    static List<Integer> pre = new ArrayList<>();
    static List<Integer> pos = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		G = Integer.parseInt(br.readLine());
		
		// 우선 각 약수를 구하는 리스트
		int pivot = (int) Math.sqrt(G); // 약수를 탐색할 때 반환점
		
        // 이제 약수를 하나씩 탐색한다.
        for(int i = 1; i <= pivot; i++) {
            // 만약 자연수가 아니라면
            if(G % i != 0) continue;
            pre.add(i);
            pos.add(G / i);
        }
        
        // 그리고 이제 몸무게를 구할 차례이다.
        // 몸무게는 두 약수의 합/2로 구할건데, 만약에 두 약수의 합이 홀수라면, 못구함
        for(int i = 0; i < pre.size(); i++) {
            if(pre.get(i) == pos.get(i)) continue;
            int sum = pre.get(i) + pos.get(i);
            if((sum) % 2 != 0) continue;
            pq.offer(sum/2);
            
        }
        
        if(pq.size() == 0) {
            sb.append(-1);
        }
        else {
           while(!pq.isEmpty()) {
            sb.append(pq.poll()).append('\n');
            } 
        }
        

        
        bw.write(sb.toString());
        bw.close();
	}
}
