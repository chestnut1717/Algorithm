import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    pq.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		    
		}
		
		int cnt = 1;
		int pivot = pq.poll().end;
		while(!pq.isEmpty()) {
		    Pair nextPair = pq.poll();
		    int nextStart = nextPair.start;
		    int nextEnd = nextPair.end;
		    // 다음 시작시간이 이전 종료시간보다 크거나 같을 경우
		    if(nextStart >= pivot) {
		        pivot = nextEnd;
		        cnt++;
		    }
		}
		
		System.out.println(cnt);
		
	}
	
	static class Pair implements Comparable<Pair> {
	    int start;
	    int end;
	    public Pair(int start, int end) {
	        this.start = start;
	        this.end = end;
	    }
	    
	    @Override
	    public int compareTo(Pair o) {
	        if(this.end == o.end) {
	            return Integer.compare(this.start, o.start);
	        }
	        return Integer.compare(this.end, o.end); 
	            
	    }
	    
	}
}