import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static List<Pair> list = new ArrayList<>();
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static int result;
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st;
        
        // 숙제의 개수 입력
        N = Integer.parseInt(br.readLine());
        
        // 숙제의 개수와 더불어 각 문제 번호, 데드라인, 컵라면 수 입력
        for(int num = 1; num <= N; num++) {
            st = new StringTokenizer(br.readLine());
            
            int deadline = Integer.parseInt(st.nextToken());
            int amt = Integer.parseInt(st.nextToken());
            
            list.add(new Pair(num, deadline, amt));
            
            
        }
        
        // 리스트를 데드라인 순서대로 정렬을 하자.
        list.sort((a, b) -> Integer.compare(a.deadline, b.deadline));
        
        // Collections.sort(list, (a, b) -> Integer.compare(a.deadline, b.deadline));
        
        for(Pair p: list) {
            int deadline = p.deadline;
            pq.add(p); // 우선 넣고
            // 데드라인보다 pq 사이즈가 더 크면(해당 일에 처리할 수 있는 것보다 많은 숙제 주어짐)
            if(deadline < pq.size()) {
                pq.poll(); // 여기서는 이제 컵라면 amt가 가장 작은 친구가 나감
            }
        }
        
        
        while(!pq.isEmpty()) {
            result += pq.poll().amt;
        }
        
        
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
	}
}

class Pair implements Comparable<Pair>{
    int num, deadline, amt; // int로 다 처리할 수 있음(2^31미만 자연수)
    public Pair(int num, int deadline, int amt) {
        this.num = num;
        this.deadline = deadline;
        this.amt = amt;
    }
    
    // 컵라면 기준 오름차순 정렬
    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.amt, o.amt);
    }
}