import java.util.*;
import java.io.*;
 
 /**
 * @since jdk1.8
 * - 문제 : SWEA 6808 규영이와 인영이의 카드게임
 * - 소요시간 : 1시간 
 * - 난이도 : 중
 * - 아이디어 :시간복잡도는 O(N!)인데다, N이 9이니 제한 시간 내에 완전탐색 => 순열로도 가능하다.
 * - 우선 A가 받은 카드를 기준으로 B는 A가 가지고 있지 않는 카드를 나누어 준 다음
 * - B의 경우의 수를 하나 구할 때마다, 점수를 계산해서 승패를 계산한다.
 * - 모든 경우의 수를 구할 때까지 반복
 */
class Solution {
    static int T;
    static int[] tmpA, A;
    static int[] tmpB, B;
    static int[] num; // B가 낼 수 있는 후보군
    static boolean[] visited;
    static int win, lose; // 승패
    public static void main(String args[]) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine()); // T 입력
         
        // 각 테스트 케이스
        for(int test_case = 1; test_case <= T; test_case++) {
            // 1~18까지 인덱스를 가지고 카드 유무 파악하기 위함
            tmpA = new int[19];
            tmpB = new int[19];
            A = new int[9];
            B = new int[9];
            num = new int[9];
            visited = new boolean[9];
            win = 0; lose = 0;
             
            // A, B 카드 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 9; i++)  {
                int n = Integer.parseInt(st.nextToken());
                tmpA[n] = 1;
                tmpB[n] = 1;
            }
            // B가 가지고 있는 카드
            int idxA = 0;
            int idxB = 0;
            for(int i = 1; i <= 18; i++) {
                if(tmpA[i] == 1) {A[idxA] = i; idxA++;} 
                if(tmpB[i] == 0) {B[idxB] = i; idxB++;}// tmpB의 특정 idx가 0이라는 것은, A가 받았다는 것 => 즉, B의 차례
 
                 
            }
            // 순열 계산
            perm(9, 9, 0);
            // 정답 출력
            System.out.printf("#%d %d %d%n", test_case, win, lose);
             
             
        }
         
         
    }
    // 순열 공식
    static void perm(int n, int r, int cnt) {
        if(cnt == r) {
            if (aWin() == 1) win++; // a가 이기면 win더해주기
            else if (aWin() == -1) lose++; // b기 이기면 lose 더해주기
        }
        for(int i = 0; i < r; i++) {
            if (visited[i] == false) { // 현재 방문한 상태가 아닐 때만 순열의 후보군으로 더해줌
                num[cnt] = B[i];
                visited[i] = true;
                perm(n, r, cnt+1);
                visited[i] = false;
            }
 
        }
    }
    // a가 이기면 1, b가 이기면 -1, 무승부면 0 반환
    static int aWin() {
        int sumA = 0;
        int sumB = 0;
        for(int i = 0; i < 9; i++) {
            if(A[i] > num[i]) {
                sumA += A[i];
                sumA += num[i];
            } else if (A[i] < num[i]) {
                sumB += A[i];
                sumB += num[i];
            }
        }
        if(sumA > sumB) return 1;
        else if (sumA == sumB) return 0;
        else return -1;
         
    }
}