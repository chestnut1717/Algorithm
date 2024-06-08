import java.util.*;
import java.io.*;

public class Main
{
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static long[] dist;
    static boolean[] visited;
    static List<Edge> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		

		
		// M개의 노선 개수 입력
		for(int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    int dist = Integer.parseInt(st.nextToken());
		    list.add(new Edge(start, end, dist)); // 간선 저장
		}
		
		// 음수 가중치도 있기 때문에 벨만포드 사용
		dist = new long[N+1];
		Arrays.fill(dist, INF); // 모든 숫자 INF로 초기화
		
		boolean isCycle = bellmanFord();
		if(isCycle) {
		    sb.append(-1);
		} else {
		    for(int i = 2; i <= N; i++) {
		       if(dist[i] == INF) {
    		        sb.append(-1).append('\n');
    		    } else {
    		        sb.append(dist[i]).append('\n');
    		    } 
		    }
		}
		
		
		bw.write(sb.toString());
		bw.close();
		
	}
	// 음수 가중치므로 벨만포드 가즈아
	static boolean bellmanFord() {
	    dist[1] = 0;
	    // 우선 모든 정점마다 각각의 간선을 확인해야 함
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < list.size(); j++) {
	            Edge e = list.get(j);
	            int start = e.start;
	            int end = e.end;
	            int d = e.dist;
	            
	            // 이제 현재 간선보다 넥스트 노드가 더 짧은 경우
	            if(dist[start] != INF && dist[end] > dist[start] + d) {
	                dist[end] = dist[start] + d;
	                // 만약 cycle이 생기는 경우가 발생한다면
	                if( i == N-1) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	    
	}
	
	static class Edge {
	    int start, end, dist;
	    public Edge(int start, int end, int dist) {
	        this.start = start;
	        this.end = end;
	        this.dist = dist;
	    }
	}
}
