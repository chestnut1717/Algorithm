
import java.util.*;
import java.io.*;
public class Main {
    static final int N = 5;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static char[][] map;
    static int[] combX = new int[25];
    static int[] combY = new int[25];
    static int result = 0;

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        // 입력받기
        map = new char[N][N];
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            char[] tmp = st.nextToken().toCharArray();
            for(int x = 0; x < N; x++) {
                map[y][x] = tmp[x];
            }
        }
        
        // 좌표 미리 계산하기
        for(int i = 0; i < N*N; i++) {
            combY[i] = i / N;
            combX[i] = i % N;
        }
        
        // 조합 구해서 각 조합마다 경우의 수 구하기        
        combination(new int[7], 0, 0, 7);
        System.out.println(result);

    }
    
    // 모든 조합 구하기
    static void combination(int[] comb, int idx, int depth, int left) {
        // 만약 0이라면
        if(left==0) {
            bfs(comb);
            return;
        }

        if(depth == 25) return;
        
        comb[idx] = depth;
        combination(comb, idx+1, depth+1, left-1);
        combination(comb, idx, depth+1, left);
    }
    
    static void bfs(int comb[]) {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[7];
        
        visited[0] = true;
        q.add(comb[0]);
        int cnt = 1, sCnt = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(map[combY[cur]][combX[cur]] == 'S') sCnt++;
            
            for(int i = 0; i < 4; i++) {
                for(int next=1; next < 7; next++) {
                    if(!visited[next] && combX[cur] + dx[i] == combX[comb[next]] && combY[cur]+dy[i] == combY[comb[next]]) {
                        visited[next]=true;
                        q.add(comb[next]);
                        cnt++;
                    }
                }
            }
        }
        if(cnt == 7 && sCnt >= 4) result++;
    }


}

