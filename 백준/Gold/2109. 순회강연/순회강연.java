import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static List<Pair> lectures = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n 입력 받기
        n = Integer.parseInt(br.readLine());

        // n개의 강의 정보 입력 받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            lectures.add(new Pair(p, d));
        }
        
        // 이제 여기서 정렬 기준을 맨 앞으로 한다.
        Collections.sort(lectures);
        // 여기서 이제 내림차순 정렬된 p기준으로 하나씩 찾아보고,
        // 혹시라도 이미 겹친 시간이면 무시한다.
        for(Pair lec: lectures) {
            int time = lec.d;
            int fee = lec.p;
            // 시간 앞 순서대로 정렬을 한 다음, limit을 파악한다.
            
            // 우선순위큐로 해야 함
            // 먼저 기간 오름차 + 비용 내림차로 정렬을 한 다음
            // 거기에서 할당할 수 있는 일보다 더 많으면 poll해야 하는데,
            // 이 예제를 처리해야 함 => linear하게 하면 20을 고려 못함 => 20이 나중에 들어오더라도 10이 나오도록 ㅐㅎ야 함
            // 4
            // 10 1
            // 20 2
            // 30 2
            // 40 3
            pq.offer(fee);
            if(pq.size() > time) {
                pq.poll();
            }
 

        }
        
        while(!pq.isEmpty()) {
            result += pq.poll();
        }
        System.out.println(result);
        
    }
}

// Pair 클래스 정의
class Pair implements Comparable<Pair>{
    int p, d;

    public Pair(int p, int d) {
        this.p = p; // fee
        this.d = d; // time
    }
    
    @Override
    public int compareTo(Pair o) {
        if (this.d == o.d) {
            return Integer.compare(o.p, this.p);
        }
        return Integer.compare(this.d, o.d);
    }
}