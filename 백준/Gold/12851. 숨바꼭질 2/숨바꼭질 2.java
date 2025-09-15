import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] result = new int[100001]; // 최단 거리 저장
    static int[] count = new int[100001];  // 경로 수 저장
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q.offer(N);
        result[N] = 1;
        count[N] = 1; 

        bfs();

        System.out.println(result[K]-1); // 최단 거리
        System.out.println(count[K]);  // 최단 경로 수
    }

    static void bfs() {
        while(!q.isEmpty()) {
            int now = q.poll();
            int[] moves = {now+1, now-1, now*2};
            
            for(int next: moves) {
                if(next < 0 || next > 100000) continue;
                // 만약 처음 방문이라면 result 채우고 q에 넣기
                if(result[next] == 0) {
                    result[next] = result[now] + 1;
                    count[next] = count[now];
                    q.offer(next);
                }
                // 방문했다면 count로 지금까지 몇 개인지 채움
                else if (result[next] == result[now] + 1){
                    count[next] += count[now];
                }
            }
        }
    }
}
