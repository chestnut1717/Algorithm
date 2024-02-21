import java.util.*;
import java.io.*;
/**
 * @since jdk1.8

 * - 문제 : SWEA 3289 서로소집합
 * - 소요시간 : 50분
 * - 난이도 : 중
 * - 아이디어 : Union-Find의 정석적인 문제 => 암기하자
 */
class Solution {
    static int[] parent;
    static int N, M;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());


		for(int test_case = 1; test_case <= T; test_case++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    N = Integer.parseInt(st.nextToken()); // 1 ~ N
		    M = Integer.parseInt(st.nextToken()); // 연산 개수
		    
		    makeSet(N);
		    System.out.printf("#%d ", test_case);
		    for(int i = 0; i < M; i++) {
		        st = new StringTokenizer(br.readLine());
		        int cmd = Integer.parseInt(st.nextToken());
		        int a = Integer.parseInt(st.nextToken());
		        int b = Integer.parseInt(st.nextToken());
		        
		        if (cmd == 0)  {
		            union(a, b);
		        }
		        
		        
		        else if (cmd == 1) {
		            if(findSet(a) == findSet(b)) {
		                System.out.print(1);
		            } else {
		                System.out.print(0);
		            }
		        }
		    }
		    System.out.println();
		}
		
	}
	
	// {1} ~ {N}으로 만들어주는 최초 1회만 실행해주는 코드
	static void makeSet(int x) {
	     parent = new int[N+1]; // 부모 노드를 저장하는 배열 초기화
	    for(int i = 1; i <= N; i++) {
	        parent[i] = i; // 자신의 부모를 자신으로 설정
	    }
	}
	
	// x를 포함하는 집합을 찾는 오퍼레이션
	static int findSet(int x) {
	    // 만약 부모랑 자신이 같으면 그대로 반환(루트 노드일 때)
	    if(parent[x] == x) return x;
	    // 그렇지 않는다면, 해당 트리 최고 루트를 자신의 부모 노드로 바꿈
	    else return parent[x] = findSet(parent[x]);
	}
	// a와 b 두 집합을 합친다!
	// a = 부모, b = 자식 처리
    static void union(int a, int b) {
        if (findSet(b) == findSet(a)) return;
        parent[findSet(b)] = findSet(a);
    }
	
}