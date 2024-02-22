import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * - 문제 : SWEA 3124 최소 스패닝 트리
 * - 소요시간 : 1시간 
 * - 난이도 : 중
 * - 아이디어 : 크루스칼 알고리즘을 활용해 풀 수 있음.
 * - Edge라는 클래스를 만들고 Edge객체를 담는 배열을 만든 후 정렬
 * - 그 후 union연산을 활용해 최소경로를 찾도록 한다.
 */
class Solution {
    static int V, E; // 정점, 간선
    static int[] parents;
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    V = Integer.parseInt(st.nextToken());
		    E = Integer.parseInt(st.nextToken());
		    
		    Edge[] edgeList = new Edge[E]; // 간선의 개수만큼 초기화
		    for(int i = 0; i < E; i++) {
		        st = new StringTokenizer(br.readLine());
		        
		        int A = Integer.parseInt(st.nextToken()); // from
		        int B = Integer.parseInt(st.nextToken()); // to
		        int C = Integer.parseInt(st.nextToken()); // weight
		        edgeList[i] = new Edge(A, B, C);
		    }
		    
		    
		    
		    
		    // kruskal
		    // 우선 모든 union 초기화
		    make();
		    // 각 초기화한 것마다
		    Arrays.sort(edgeList);
		    
		    // 이제 간선을 기준으로 V-1개 탐색
		    int cnt = 0;
		    long result = 0;
		    for(Edge e: edgeList) {
		        if(!union(e.from, e.to)) continue; // cycle이 생기는 것(이미 트리 안에 존재)
		        result += e.weight;
		        cnt++;
		        if(cnt == V+1) break;
		    }
		    
		    System.out.printf("#%d %d%n", test_case, result);
		    
		}
	}
	
	static void make() {
	    parents = new int[V+1];
	    for(int i = 0; i < V; i++) {
	        parents[i] = i;
	    }
	}
	
	static int find(int x) {
	    if (parents[x] == x) return x;
	    return parents[x] = find(parents[x]);
	}
	
	static boolean union(int a, int b) {
	    int rootA = find(a);
	    int rootB = find(b);
	    
	    if(rootA == rootB) return false;
	    parents[rootB] = rootA;
	    return true;
	}
	
	static class Edge implements Comparable<Edge> {
    	    int from;
    	    int to;
    	    int weight;
    	    
    	    public Edge(int from, int to, int weight) {
        	        this.from = from;
        	        this.to = to;
        	        this.weight = weight;
    	    }
    	    @Override
    	    public int compareTo(Edge e) {
    	        return this.weight - e.weight;
    	    }
	}
}