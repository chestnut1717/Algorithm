class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, visited, computers);
                answer++;
            }
        }
        
        return answer;
    }
    static void dfs(int start, boolean[] visited, int[][] computers) {
        visited[start] = true;
        for(int next = 0; next < computers.length; next++) {
            if(!visited[next] && computers[start][next] == 1) {
                dfs(next, visited, computers);
            }
        }
    }
}