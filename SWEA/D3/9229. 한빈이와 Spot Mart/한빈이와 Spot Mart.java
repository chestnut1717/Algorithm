import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 9229 한빈이와 Spot Mart
 * - 소요시간 : 25분 
 * - 난이도 : 하
 * - N이 1000이하이기 때문에 완전탐색을 고려해 볼 수 있겠으나, 투 포인터를 활용하여 문제를 풀어 봄
 * - 우선 투 포인터를 활용하기 위해 정렬을 하였고 정렬된 배열을 순차적으로 탐색하여 가능한 최댓값을 갱신함 O(NlogN) + O(N)
 */
class Solution {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 과자 개수
            int M = Integer.parseInt(st.nextToken()); // 가져갈 수 있는 최대 무게
            int[] arr = new int[N]; // 과자 무게 담을 배열
            
            // 과자의 무게입력
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            // 정렬
            Arrays.sort(arr);
            
            // 투 포인터 활용
            int max = Integer.MIN_VALUE;
            int start = 0;
            int end = N-1;
            while(start < end) { // start와 end 역전될 때
                int tmpMax = arr[start] + arr[end];
                if( tmpMax > M) { // 합이 M보다 크다면 end줄여주기
                    end--; // end index를 감소시켜준다
                }
                else {
                    max = Math.max(max, tmpMax); // 최댓값 갱신
                    start++; // start index를 증가시켜 더 큰 수가 있는지 점검한다.
                }
            }
            
            if (max == Integer.MIN_VALUE) max = -1; // 만약 최댓값이 없다면 => 조건대로 -1 출력하도록 함
            System.out.printf("#%d %d%n", test_case, max);

        }
        
    }
}