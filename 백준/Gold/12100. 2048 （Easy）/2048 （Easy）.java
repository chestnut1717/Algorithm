import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int result = 0;  // 최대 블록 값 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 백트래킹 실행 (최대 5번 이동)
        recur(arr, 0);

        System.out.println(result);
    }

    static void recur(int[][] arr, int depth) {
        if (depth == 5) { // 5번 이동 후 최댓값 갱신
            findMax(arr);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] nextArr = move(arr, i);
            recur(nextArr, depth + 1);
        }
    }

    static void findMax(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, arr[i][j]);
            }
        }
    }

    static int[][] move(int[][] arr, int direction) {
        int[][] tmp = new int[N][N];
        boolean[][] merged = new boolean[N][N]; // 한 번만 합쳐지도록 체크

        for (int i = 0; i < N; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, N);
        }

        switch (direction) {
            case 0: // 오른쪽
                for (int r = 0; r < N; r++) {
                    for (int c = N - 2; c >= 0; c--) { // 오른쪽으로 이동
                        if (tmp[r][c] == 0) continue;

                        int next = c;
                        while (next + 1 < N && tmp[r][next + 1] == 0) {
                            next++;
                        }

                        if (next + 1 < N && tmp[r][next + 1] == tmp[r][c] && !merged[r][next + 1]) {
                            tmp[r][next + 1] *= 2;
                            tmp[r][c] = 0;
                            merged[r][next + 1] = true;
                        } else {
                            if (next != c) {
                                tmp[r][next] = tmp[r][c];
                                tmp[r][c] = 0;
                            }
                        }
                    }
                }
                break;

            case 1: // 아래
                for (int c = 0; c < N; c++) {
                    for (int r = N - 2; r >= 0; r--) {
                        if (tmp[r][c] == 0) continue;

                        int next = r;
                        while (next + 1 < N && tmp[next + 1][c] == 0) {
                            next++;
                        }

                        if (next + 1 < N && tmp[next + 1][c] == tmp[r][c] && !merged[next + 1][c]) {
                            tmp[next + 1][c] *= 2;
                            tmp[r][c] = 0;
                            merged[next + 1][c] = true;
                        } else {
                            if (next != r) {
                                tmp[next][c] = tmp[r][c];
                                tmp[r][c] = 0;
                            }
                        }
                    }
                }
                break;

            case 2: // 왼쪽
                for (int r = 0; r < N; r++) {
                    for (int c = 1; c < N; c++) {
                        if (tmp[r][c] == 0) continue;

                        int next = c;
                        while (next - 1 >= 0 && tmp[r][next - 1] == 0) {
                            next--;
                        }

                        if (next - 1 >= 0 && tmp[r][next - 1] == tmp[r][c] && !merged[r][next - 1]) {
                            tmp[r][next - 1] *= 2;
                            tmp[r][c] = 0;
                            merged[r][next - 1] = true;
                        } else {
                            if (next != c) {
                                tmp[r][next] = tmp[r][c];
                                tmp[r][c] = 0;
                            }
                        }
                    }
                }
                break;

            case 3: // 위
                for (int c = 0; c < N; c++) {
                    for (int r = 1; r < N; r++) {
                        if (tmp[r][c] == 0) continue;

                        int next = r;
                        while (next - 1 >= 0 && tmp[next - 1][c] == 0) {
                            next--;
                        }

                        if (next - 1 >= 0 && tmp[next - 1][c] == tmp[r][c] && !merged[next - 1][c]) {
                            tmp[next - 1][c] *= 2;
                            tmp[r][c] = 0;
                            merged[next - 1][c] = true;
                        } else {
                            if (next != r) {
                                tmp[next][c] = tmp[r][c];
                                tmp[r][c] = 0;
                            }
                        }
                    }
                }
                break;
        }

        return tmp;
    }
}
