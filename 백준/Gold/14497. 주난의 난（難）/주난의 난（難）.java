import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    static int y1, x1, y2, x2; // y, x 순서로 통일
    static char[][] arr = new char[304][304];
    static int[][] visited = new int[304][304];
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static ArrayDeque<Integer> dq = new ArrayDeque<>(); // 전역 q

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
                
        for (int y = 0; y < N; y++) {
            char[] str = br.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                arr[y][x] = str[x];
            }
        }
        
        // 초기 출발 값 집어넣기
        dq.offerFirst(1000 * y1 + x1);
        visited[y1][x1] = 1;
        int cnt = 0;
        
        // 0-1 BFS
        while(!dq.isEmpty()) {
            int pos = dq.pollFirst();
            int y = pos / 1000;
            int x = pos % 1000;
            
            
            if (y == y2 && x == x2) {
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(!checkBound(ny, nx) || visited[ny][nx] != 0) continue;
                
                if(arr[ny][nx] != '0') {
                    arr[ny][nx] = '0';
                    visited[ny][nx] = visited[y][x]+1;
                    dq.offerLast(ny*1000 + nx);
                } else {
                    visited[ny][nx] = visited[y][x];
                    dq.offerFirst(ny*1000 + nx);
                }
            }
        }

        
        System.out.print(visited[y2][x2] - 1);
        
    }

    static boolean checkBound(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}
