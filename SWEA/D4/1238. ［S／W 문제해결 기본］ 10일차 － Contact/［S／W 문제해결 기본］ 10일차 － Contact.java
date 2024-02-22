import java.io.*;
import java.util.*;
/**
 *  @since jdk1.8
 * - 문제 : SWEA 1238 Contact
 * - 소요시간 : 20분
 * - 난이도 : 하
 * - 아이디어 : BFS와 인접리스트를 활용하면 된다.
 * - 우선 BFS를 사용한 이유는, 연락을 취하는 단위시간은 모두 동일하기 때문에(모두 동일한 거리) BFS를 사용해서 일종의 거리 문제 풀이 가능
 * - 그리고 인접리스트와 Node custom class를 활용해서 정점과 간선의 관계를 표현
 * - 큐에서 BFS를 돌릴 때에는처음 시작점과 연결된 모든 Node들을 연결리스트에 꺼내어 넣음
 * - 이때 큐에서 poll()한 노드와 연결된 새로운 노드들을 연결리스트에 넣을 때, Node클래스 내의 cnt를 이전 cnt보다 1 증가시킴으로써 Node가 퍼지는 속도 조절 가능
 * - 만약 큐에서 새로운 Node의 cnt가 지금까지 나온 cnt(maxCnt)보다 무조건 크다면 => 새로운 Phase => 갱신
 * - 동시에 maxNum를 갱신하면서 현재 Phase에 가장 큰 번호는 누구인지 체크할 수 있음
 */
class Solution{
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
        
		for(int test_case = 1; test_case <= T; test_case++) {
		    // 첫번째줄 입력
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int N = Integer.parseInt(st.nextToken());
		    int start = Integer.parseInt(st.nextToken());
		    
		    // 사전에 미리 100개를 만들어놓은다.(문제 조건)
		    List<List<Node>> arr = new ArrayList<>();
		    for(int i = 0; i < 101; i++) {
		        arr.add(new LinkedList<Node>());
		    }
		    // 두번째줄 입력
		    st = new StringTokenizer(br.readLine());
		    for(int i = 0; i < N/2; i++) {
		        int from = Integer.parseInt(st.nextToken());
		        int to = Integer.parseInt(st.nextToken());
		        arr.get(from).add(new Node(to));
		        
		    }
		    
		    Queue<Node> q = new ArrayDeque<>();

		    // Q에 집어넣고 시작하기!
		    for(Node node: arr.get(start)) {
		        node.cnt = 1;
		        q.add(node);
		    }
		    
		    int maxCnt = 0; // 진행한 Phase
		    int maxNum = 0; // 현재 Phase에서 가장 node num이 큰 값
		    boolean[] visited = new boolean[101];
		    
		    // BFS
		    while(!q.isEmpty()) {
		        Node node = q.poll();
		        // 이미 방문하였으면 무시
		        if(visited[node.num]) continue;
		        visited[node.num] = true;
		        
		        // 새로운 phase인지 check
		        if(node.cnt > maxCnt) {
		            maxCnt = node.cnt;
		            maxNum = node.num;
		        }
		        // 기존의 phase라면 node num 갱신 여부 체크
		        else if (node.num > maxNum) {
		            maxNum = node.num;
		        }
		        // 그리고 poll()에서 뽑은 노드와 연결된 노드들을모두 넣어줌
		        for(Node n: arr.get(node.num)) {
		            n.cnt = maxCnt + 1; // 현재 node의 phase + 1을 해줘야 함
		            q.offer(n);
		        }
		    }
		    
		    System.out.printf("#%d %d%n", test_case, maxNum);

		}
	}
	
	static class Node {
	    int num;
	    int cnt;
	    
	    public Node(int num) {
	        this.num = num;
	    }
	}
}