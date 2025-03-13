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
		    
		    // 만약 현재 시간보다 도착 시간이 더 길 경우
		    // 현재 시간은 도착시간 + 걸리는 시간으로 변경
		    
		    if(resultTime < at) {
		        resultTime = at + dt;
		    }

		    // 현재 시간이 더 크거나 같으면 소요시간만 더 추가
		    else {
		        resultTime += dt;
		    }
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