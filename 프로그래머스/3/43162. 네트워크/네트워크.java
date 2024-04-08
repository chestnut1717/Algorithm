import java.util.*;
import java.io.*;
// 1:10;
class Solution {
    static int N; // 컴퓨터 개수
    static List<List<Integer>> list = new ArrayList<>(); // 인접리스트 생성
    static int result = 0;
    static boolean visited[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        N = computers.length;
        // 인접리스트 초기화
        for(int i = 0; i < N; i++) {
            list.add(new ArrayList<Integer>());
        }
        
        // 그리고 입력을 받음
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                if(computers[i][j] == 1) {
                    list.get(i).add(j);
                }
            }
        }
        
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                bfs(i);
                result++;
            }
        }
        
        return result;
    }
    
    static void bfs(int num) {
        // bfs를 통해서 해당 점과 인접한 connected component 찾아냄
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);
        visited[num] = true;
        
        while(!q.isEmpty()) {
            int nowNum = q.poll();
            for(int nextNum: list.get(nowNum)) {
                if(!visited[nextNum]) {
                    visited[nextNum] = true;
                    q.offer(nextNum);
                }
            }
        }
        
    }
}