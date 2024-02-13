import java.util.*;
import java.io.*;
  
 /**
 * @since jdk1.8
 * - 문제 : SWEA 6808 규영이와 인영이의 카드게임
 * - 소요시간 : 1시간 
 * - 난이도 : 중
 * - 아이디어 : nextPermutation으로 풀어봄
 */
class Solution {
    static int T;
    static int[] tmpA, A;
    static int[] tmpB, B;
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
            // np 쓰기 전 정렬
            Arrays.sort(B);
            // 순열 계산
            do{
                if (aWin() == 1) win++;
                if (aWin() == -1) lose++;
            } while(nextPermutation());
            // 정답 출력
            System.out.printf("#%d %d %d%n", test_case, win, lose);
              
              
        }
          
          
    }
    // 순열 공식
    static boolean nextPermutation() {
        int N = 9;
        int i = N-1;
        /// step1. 꺾이는 구간 찾기(내림차순 끝나는 구간!)
        while(i > 0 && B[i-1] >= B[i]) i--;
        if (i == 0) return false;
        
        // step2. 그 구간과 바꿀 숫자 찾기
        int j = N-1;
        while(B[i-1] >= B[j]) j--;
        
        // step3. swap
        swap(i-1, j);
        
        // step4 나머지 다 오름차순으로 정렬
        int k = N-1;
        while(k > i) {
            swap(i, k);
            i++;
            k--;
        }
        return true;
        
    }
    static void swap(int i, int j) {
        int tmp = B[i];
        B[i] = B[j];
        B[j] = tmp;
    }
    
    // a가 이기면 1, b가 이기면 -1, 무승부면 0 반환
    static int aWin() {
        int sumA = 0;
        int sumB = 0;
        for(int i = 0; i < 9; i++) {
            if(A[i] > B[i]) {
                sumA += A[i];
                sumA += B[i];
            } else if (A[i] < B[i]) {
                sumB += A[i];
                sumB += B[i];
            }
        }
        if(sumA > sumB) return 1;
        else if (sumA == sumB) return 0;
        else return -1;
          
    }
}