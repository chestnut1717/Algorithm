import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        String[] board = new String[N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        int minCount = N * M;

        for (int i = 0; i < N - 7; i++) {
            int rStart = i;
            int rEnd = i + 8;

            for (int j = 0; j < M - 7; j++) {
                int cStart = j;
                int cEnd = j + 8;
                int whiteFirst = 0;
                int blackFirst = 0;

                for (int r = rStart; r < rEnd; r++) {
                    String row = board[r].substring(cStart, cEnd);

                    for (int c = 0; c < row.length(); c++) {
                        char element = row.charAt(c);

                        // 행과 열의 합이 짝수일 때
                        if ((r + c) % 2 == 0) {
                            if (element != 'W') {
                                whiteFirst++;
                            }
                            if (element != 'B') {
                                blackFirst++;
                            }
                        }
                        // 행과 열의 합이 홀수일 때
                        else {
                            if (element != 'B') {
                                whiteFirst++;
                            }
                            if (element != 'W') {
                                blackFirst++;
                            }
                        }
                    }
                }

                minCount = Math.min(minCount, whiteFirst);
                minCount = Math.min(minCount, blackFirst);
            }
        }

        System.out.println(minCount);
    }
}
