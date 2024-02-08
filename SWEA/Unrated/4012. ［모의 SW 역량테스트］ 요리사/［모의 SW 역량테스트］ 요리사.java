import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * - 문제 : SWEA 4012 요리사
 * - 소요시간 : 2시간 
 * - 난이도 : 중
 * - 아이디어 : N개의 식재료에서 A와 B에게 각각 N/2개씩 나눠 주는 것을 flag 배열을 활용해서 풀었다.
 * - flag배열과 flag의 인덱스를 활용해 (ex. [0, 1, 0, 1]이면 A에게 2번, 4번 식재료(index기준)으로 나눠주고, B는 그 반대인 1, 3식재료 줌) 식재료 분배
 * - 마지막으로 식재료의 시너지는 2중 for문을 활용해서 A, B의 음식 점수의 절댓값을 구한 다음 => 매 식재료 조합을 구할 때마다 갱신
 */
class Solution {
    static int N; // row
    static int[][] arr; // 음식 담을 배열
    static int[] A; // A의 식재료 조합
    static int[] B; // B의 식재료 조합
    static int[] numbers; // flag배열
    static int result; // 최솟값
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T;
        T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine()); // row
 
            // 배열 입력
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            // 모든 조합의 수 구하기(매 테스트 케이스마다 초기화)
            A = new int[N / 2]; // A, B 둘 다 식재료는 N/2개밖에 들고가지 못하기 때문
            B = new int[N / 2];
            numbers = new int[N];
            result = Integer.MAX_VALUE; 
             
            // 조합 구함
            combi(N, N / 2, 0, 0);
 
            System.out.printf("#%d %d%n", test_case, result);
 
        }
    }
    // 식재료 조합 및 최솟값 산출 메서드
    static void combi(int n, int r, int start, int cnt) {
        // 최대 r개의 1이 올 때, 종료 (r = N/2)
        if (cnt == r) {
            setArr(); // A, B의 식재료 세팅
            result = Math.min(result, calc()); // A, B음식 점수 합 갱신
            return;
        }
        // 종료 조건에 다다를 때까지, flag함수의 조합을 구해줌
        for (int i = start; i < n; i++) {
            numbers[i] = 1; 
            combi(n, r, i + 1, cnt + 1);
            numbers[i] = 0;
        }
    }
     
    // flag배열을 활용해 A, B의 식재료 조합을 산출해줌
    static void setArr() {
        int idx = 0;
        int jdx = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1) {
                A[idx] = i;
                idx++;
 
            } else {
                B[jdx] = i;
                jdx++;
            }
 
        }
    }
     
    // A, B 음식 시너지 점수의 합과 차이를 구해주는 메서드
    static int calc() {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                int y = A[i];
                int x = A[j];
                if (y == x)
                    continue;
                sumA += arr[y][x];
            }
        }
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int y = B[i];
                int x = B[j];
                if (y == x)
                    continue;
                sumB += arr[y][x];
            }
        }
 
        return Math.abs(sumA - sumB);
 
    }
 
}