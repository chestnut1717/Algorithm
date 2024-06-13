import java.util.*;
import java.io.*;

public class Main
{
    // 각각의 테스트 케이스마다 정점, 간선의 개수 초기화
    // 간선의 개수마다 우선 입력을 받음
    // 인접 리스트에 간선을 저장한다.
    // 그래서 인접 리스트에서 DFS탐색을 한다.
    // 탐색하면서 이미 방문하지 않은 것이라면 => cnt++;
    // 근데 방문을 끝냈는데, cycle이 있다면 cnt--;
    // 
    static int N, M;
    static List<List<Integer>> list;
    static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test = 1;
		while(true) {

            st = new StringTokenizer(br.readLine());
    	    N = Integer.parseInt(st.nextToken());
    	    M = Integer.parseInt(st.nextToken());
    	    
    	    if(N == 0 && N == 0) break;
    	    
    	    list = new ArrayList<>(); // 인접 리스트
    	    // 인접 리스트 초기화
    	    for(int i = 0; i < N+1; i++) {
    	        list.add(new ArrayList<>());
    	    }
    	    
    	    // 간선 입력
    	    for(int i = 0; i < M; i++) {
    		    st = new StringTokenizer(br.readLine());
    		    int start = Integer.parseInt(st.nextToken());
    		    int end = Integer.parseInt(st.nextToken());
    		    // 인접 리스트 저장
    		    list.get(start).add(end);
    		    list.get(end).add(start);
    		    
    		  
    	    }
    	    // 간선 가지고 DFS탐색
    	    visited = new boolean[N+1];
    	    
    	    int cnt = 0; // 트리의 개수
    	    
    	    for(int n = 1; n <= N; n++) {
    	        if(visited[n]) continue;
    	        cnt += checkTree(n);
	            
    	    }
    	    
    	    printTree(test, cnt);
    	    test++;
    	    
		}
		

	}
	static void printTree(int test, int cnt) {
	    switch(cnt) {
	        case 0 :
	            System.out.printf("Case %d: No trees.\n", test);
	            break;
	        case 1:
	            System.out.printf("Case %d: There is one tree.\n", test);
	            break;
	        default:
	            System.out.printf("Case %d: A forest of %d trees.\n", test, cnt);
	    }
	}
	static int checkTree(int root) {
	    // node를 저장할 q
        Queue<Integer> q = new ArrayDeque<>();
        int nodeCnt = 0;
        int edgeCnt = 0;
        q.offer(root);
        while(!q.isEmpty()) {
            int now = q.poll();
            if(visited[now]) continue;
            visited[now] = true;
            nodeCnt++;
            
            // 이제 edge를 탐색할 차례
            for(int next: list.get(now)) {
                edgeCnt++; // 방문 여부와 상관없이 edge는 존재할 수 있으므로 더해준다.
                if(visited[next]) continue;
                q.offer(next);
            }
        }
        
        // edge의 개수는 node의 개수보다 1 커야 함(양방향이므로 /2 해준다)
        return ((edgeCnt / 2) + 1 == nodeCnt) ? 1 : 0;
        
	}
}
