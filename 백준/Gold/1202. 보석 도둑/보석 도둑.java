import java.util.*;
import java.io.*;

public class Main
{
    static int N, K; // 보석 개수, 보석 정보
    static long maxResult;
    static List<Gem> list = new ArrayList<>();
    static List<Bag> bags = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순 힙
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// N개의 개수만큼 입력
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
    		int m = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
		    list.add(new Gem(m, v));
		}
		
		// K개의 가방 입력
		for(int i = 0; i < K; i++) {
		    int c = Integer.parseInt(br.readLine());
		    bags.add(new Bag(c));
		}
		
		// 보석 무게순으로 정렬
	    Collections.sort(list);
	    // 가방도 무게순으로 정렬
	    Collections.sort(bags);
	    
	    // 이제 하나씩 line-sweep하면서 비교
	    int j = 0;
	    for(Bag bag: bags) {
	        // 아직 모든 보석을 탐색중이며, 보석의 무게가, 기존 보석의 무게보다 크지 않을때 다 넣는다.
	        while(j < N && list.get(j).m <= bag.c) {
	            pq.add(list.get(j).v);
	            j++;
	        }
	        // 그리고 다 탐색했다면, pq에 값이 존재하면 해당 값을 넣어준다.
	        if(pq.size() > 0) {
	            maxResult += pq.poll();
	        }
	    }
	    

	    
	    System.out.println(maxResult);
	}
}

class Gem implements Comparable<Gem>{
    int m, v;
    public Gem(int m, int v) {
        this.m = m; // 무게
        this.v = v; // 가치
    }
    
    // 가격 내림차, 무게 오름차
    @Override
    public int compareTo(Gem o) {
        return Integer.compare(this.m, o.m);
    }
}

class Bag implements Comparable<Bag> {
    int nowV = 0;
    int c;
    
        public Bag(int c) {
        this.c = c; // 가방 한계
    }
    
    // 무게 오름차
    @Override
    public int compareTo(Bag o) {
        return Integer.compare(this.c, o.c);
    }
}