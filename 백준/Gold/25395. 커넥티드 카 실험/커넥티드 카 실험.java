import java.util.*;
import java.io.*;

public class Main {
    static int N, S;
    static int[] carInitPos, carFuel;
    static boolean[] visited;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()) - 1; // Indexing by 0-based

        carInitPos = new int[N];
        carFuel = new int[N];
        visited = new boolean[N];

        // Reading car positions
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            carInitPos[i] = Integer.parseInt(st.nextToken());
        }

        // Reading car fuel
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            carFuel[i] = Integer.parseInt(st.nextToken());
        }

        bfs(S);

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (int car : result) {
            sb.append((car + 1)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int car = queue.poll();
            result.add(car);

            int pos = carInitPos[car];
            int fuel = carFuel[car];

            int lowerBound = pos - fuel;
            int upperBound = pos + fuel;

            // Connect cars within the fuel range
            for (int i = car - 1; i >= 0 && carInitPos[i] >= lowerBound; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
            for (int i = car + 1; i < N && carInitPos[i] <= upperBound; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
