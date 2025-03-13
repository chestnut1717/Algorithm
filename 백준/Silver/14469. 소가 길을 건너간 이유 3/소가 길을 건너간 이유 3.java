import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 소 마리(<=100)
    static int resultTime; // 결과( 최대 1000000 * 100 = 10억이하)
    static PriorityQueue<Cow> pq = new PriorityQueue<>();
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    int arrTime = Integer.parseInt(st.nextToken());
		    int durTime = Integer.parseInt(st.nextToken());
		    
		    // pq에 시간 기준 오름차순 정렬할 수 있도록 넣는다.
		    pq.add(new Cow(arrTime, durTime));
		    
		}
		
		while(!pq.isEmpty()) {
		    Cow cow = pq.poll();
		    int at = cow.arrTime;
		    int dt = cow.durTime;
		      
		    // resultTime과 도착시간만 갱신한 후 dt를 추가해주면 된다.
		    resultTime = Math.max(resultTime, at);
		    resultTime += dt;
		}
		
		System.out.println(resultTime);
		
	}
}

class Cow implements Comparable<Cow> {
    int arrTime;
    int durTime;
    
    public Cow(int arrTime, int durTime) {
        this.arrTime = arrTime;
        this.durTime = durTime;
    }
    
    // 도착시간 기준으로 오름차순 정렬
    @Override
    public int compareTo(Cow o) {
        return Integer.compare(this.arrTime, o.arrTime);
    }
}