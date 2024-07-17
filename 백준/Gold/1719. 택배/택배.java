import java.util.*;
import java.io.*;


public class Main
{
    static int n, m;
    // 인접 리스트 만들기
    static List<List<Node>> list = new ArrayList<>();
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    map = new int[n+1][n+1];
	    // 리스트 초기화
	    for(int i = 0; i < n+1; i++) {
	        list.add(new ArrayList<>());
	    }
	    
	    // 각 경로의 개수
	    for(int i = 0; i < m; i++) {
	        st = new StringTokenizer(br.readLine());
	        int start = Integer.parseInt(st.nextToken());
	        int end = Integer.parseInt(st.nextToken());
	        int weight = Integer.parseInt(st.nextToken());
	        
	        list.get(start).add(new Node(end, weight));
	        list.get(end).add(new Node(start, weight));

	    }
	    
	    // 집하장 번호 담을 배열 초기화
	    
	    // 이제 1~n번노드까지 다익스트라로 탐색한다.
	    for(int i = 1; i <= n; i++) {
	        dijkstra(i);

	    }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if (map[i][j] != 0) sb.append(map[i][j]).append(' ');
                else sb.append('-').append(' ');
            }
            sb.append('\n');
        }
            


        
        bw.write(sb.toString());
	    bw.close();
		
	}
    static void dijkstra(int initStart) {
        boolean[] visited = new boolean[n+1];

        int[] dist = new int[n + 1];
        int[] first = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            if (i == initStart)
                first[i] = 0;
            else
                first[i] = i;
        }
        
        for (int i = 1; i <= n; i++) {
            if (i == initStart)
                dist[i] = 0;
            else
                dist[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(initStart, 0));
        visited[initStart] = true;
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int num = node.n;
            int weight = node.w;
                
                visited[num] = true;
            
            for (Node next : list.get(num)) {
                if(visited[next.n]) continue;
                if (dist[num] + next.w < dist[next.n]) {
                    dist[next.n] = dist[num] + next.w;
                    pq.add(new Node(next.n, dist[next.n]));
   
                    first[next.n] = num;
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            map[i][initStart] = first[i];
        }
        
        
    }

	
    static class Node implements Comparable<Node> {
        int n, w;
        public Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}