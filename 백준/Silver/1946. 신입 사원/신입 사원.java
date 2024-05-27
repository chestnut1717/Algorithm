import java.util.*;
import java.io.*;

public class Main
{
    static int T; // test case
    static int N; // 지원자의 숫자
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// test case 입력
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
		    PriorityQueue<Pair> pq = new PriorityQueue<>();
		    // 테스트케이스 진행
		    N = Integer.parseInt(br.readLine());
		    // 각 지원자의 숫자마다 반복
		    for(int i = 0; i < N; i++) {
		        st = new StringTokenizer(br.readLine());
		        // 서류 심사와 면접점수 가져오기
		        int paper = Integer.parseInt(st.nextToken());
		        int interview = Integer.parseInt(st.nextToken());
		        pq.offer(new Pair(paper, interview));
		    }
		    
		    // 이제 PQ에서 하나씩 꺼내면서
		    Pair pivot = pq.poll();
		    
		    // 정답 개수
		    int cnt = 1;
		    while(!pq.isEmpty()) {
		        Pair temp = pq.poll();
		        // 만약 인터뷰 순위마저 낮다면
		        if(pivot.interview < temp.interview) {
                    continue;		            
		        } else {
		            pivot = temp;
		            cnt++;
		        }
		    }
		    
		    System.out.println(cnt);
		    
		}
	}
	
	static class Pair implements Comparable<Pair>{
	    int paper, interview;
	    
	    public Pair(int paper, int interview){
	        this.paper = paper;
	        this.interview = interview;
	    }
	    
	    // 동석차 없음
	    @Override
	    public int compareTo(Pair o) {
	        return this.paper - o.paper;
	    }
	    
	    @Override
	    public String toString() {
	        return String.format("%d %d",this.paper, this.interview);
	    }
	}
}
