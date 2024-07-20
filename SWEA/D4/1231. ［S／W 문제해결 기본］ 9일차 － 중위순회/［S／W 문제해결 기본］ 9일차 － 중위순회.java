import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static char[] arr;
    static List<List<Integer>> leftList;
    static List<List<Integer>> rightList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            N = Integer.parseInt(br.readLine()); // 케이스 출력

            // 초기화
            arr = new char[N + 1];
            leftList = new ArrayList<>();
            rightList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                leftList.add(new ArrayList<>());
                rightList.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                String[] row = br.readLine().split(" ");

                // 정점 번호
                int num = Integer.parseInt(row[0]);

                // 대문자
                char c = row[1].charAt(0);
                arr[num] = c;

                int left, right;
                // 왼쪽
                if (row.length >= 3) {
                    left = Integer.parseInt(row[2]);
                    leftList.get(num).add(left);
                }
                // 오른쪽
                if (row.length >= 4) {
                    right = Integer.parseInt(row[3]);
                    rightList.get(num).add(right);
                }
            }

            // inorder
            sb.append("#").append(test_case).append(" ");
            inOrder(1);
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    }

    static void inOrder(int num) {
        if (!leftList.get(num).isEmpty()) {
            inOrder(leftList.get(num).get(0));
        }
        sb.append(arr[num]);
        if (!rightList.get(num).isEmpty()) {
            inOrder(rightList.get(num).get(0));
        }
    }
}
