import java.util.*;

class Solution {
    // static Map<Integer, Integer> map = new LinkedHashMap<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        
        // 1. 먼저 sources를 담을 수 있는 값을 초기화시킨다.
        int[] arr = new int[n+1];
        Arrays.fill(arr, -1);
        
        
        // 2. 그리고 roads를 인접 리스트로 만들어야 함
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < n+1; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < roads.length; i++) {
            int start = roads[i][0];
            int end = roads[i][1];
            
            // start -> end 관계를 인접 리스트에 저장
            list.get(start).add(end);
            // end -> start 관계도 저장(양방향이므로)
            list.get(end).add(start);
        }      
        
        // 2. 그 다음에 destination에서 bfs로 탐색한다.
        Queue<Integer> q = new ArrayDeque<>();
        // 후에 이미 탐색한 구역인지 파악하기 위한 배열
        boolean[] visited = new boolean[n+1];
        
        // 방문처리 해준다.
        arr[destination] = 0;
        visited[destination] = true;
        q.offer(destination);
        // 큐가 빌때까지 반복

        while(!q.isEmpty()) {
            int now = q.poll(); // 다음에 방문할 곳 꺼냄
            
            // 인접 리스트에서 다음에 갈 것들을 꺼냄
            for(int next: list.get(now)) {
                // 만약 방문했다면 무시
                if(visited[next]) continue;
                visited[next] = true;

                // arr에 얼만틈 걸렸는지 체크해준다.
                arr[next] = arr[now]+1;
                q.offer(next);
            }

            
        }
        
        // 기록한 것들 중 sources만 출력
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = arr[sources[i]];
        }

        return answer;
    }
}