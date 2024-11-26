import java.util.*;
import java.io.*;

// dp의 각 원소
// dp[now][0] = now번 마을이 일반 마을이었을 때 now번 마을을 루트로 하는 서브 트리의 우수 마을들 인구수의 최댓값
// dp[now][0] = now번 마을이 우수 마을이었을 때 now번 마을을 루트로 하는 서브 트리의 우수 마을들 인구수의 최댓값
public class Main {
    static int N;
    static int[] villeage; // 마을 수 저장
    static int[][] dp; // dp
    static boolean[] visited; // 방문 여부 체크
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // 인접 리스트
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N 입력
        N = Integer.parseInt(br.readLine());
        
        
        // 초기화
        villeage = new int[N+1];
        visited= new boolean[N+1];
        dp = new int[N+1][2];
        
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<Integer>());
        }
        // 마을 사람 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            villeage[i] = Integer.parseInt(st.nextToken());
        }
        // 간선 리스트 만듦
        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            list.get(start).add(end);
            list.get(end).add(start);
            
        }
        
        // 이제 DFS + Tree DP를 진행
        dfs(1);
        
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
    
    static void dfs(int now) {
        if(visited[now]) return; // 이미 방문한 것
        
        visited[now] = true;
        dp[now][0] = 0; // 일반 마을
        dp[now][1] = villeage[now]; // 우수 마을
        
        // now와 인접한 노드들 출력
        List<Integer> nextNodes = list.get(now);
        for(int next: nextNodes) {
            if(visited[next]) continue; // 이미 방문한 것
            dfs(next); // 파고 들어간다.
            
            // 다 파고 들어가면 이제 진행
            // 1. 현재 마을이 일반 마을인 경우
            dp[now][0] = dp[now][0] + Math.max(dp[next][0], dp[next][1]);
            
            // 2. 현재 마을이 우수 마을인 경우
            dp[now][1] = dp[now][1] + dp[next][0];
        }
    }
}

