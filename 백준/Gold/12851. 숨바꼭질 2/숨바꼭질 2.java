
import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] arr = new int[100001];
    static int result = -1, count = 0;
    static Queue<Pair> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(arr, -1);
        q.offer(new Pair(N, 0));
        arr[N] = 0;

        bfs();

        System.out.println(result);
        System.out.println(count);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int now = pair.now;
            int dist = pair.dist;

            // 목표 지점 도착 시
            if (now == K) {
                result = dist;
                count++;
            }

            int[] moves = {now - 1, now + 1, now * 2};
            for (int next : moves) {
                if (next < 0 || next > 100000) continue;
                if (arr[next] == -1 || arr[next] == dist + 1) {
                    arr[next] = dist + 1;
                    q.offer(new Pair(next, dist + 1));
                }
            }
        }
    }
}

class Pair {
    int now, dist;
    public Pair(int now, int dist) {
        this.now = now;
        this.dist = dist;
    }
}

