import java.util.*;
import java.io.*;

public class Main {
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    // static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.offer(new Pair(a, b));
        }


        Pair now = pq.poll();
        int start = now.start;
        int end = now.end;
        int result = end - start;
        while(!pq.isEmpty()) {
            Pair next = pq.poll();
            if (next.start >= start && end >= next.end) {
                continue;
            }
            else if(next.start < end) {
                result += (next.end - next.start) + (next.start - end);
            }
            // 겹치지 않을 때
            else {
                result += next.end - next.start;
                
            }
 
            start = next.start;
            end = next.end;
        }
        
        System.out.println(result);
        
        
    }
    
    static class Pair implements Comparable<Pair>{
        int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Pair o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
        
    }
}
