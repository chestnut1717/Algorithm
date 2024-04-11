// 3:24 , 3:48
import java.util.*;

class Solution {
    static int N;
    static int[] permNum;
    static int result = Integer.MIN_VALUE; // 출력할 최댓값
    public int solution(int k, int[][] dungeons) {
        // 먼저 순열에 사용될 case를 초기화해준다.
        N = dungeons.length;
        permNum = new int[N];
        
        // idx넣어줌
        for(int i = 0; i < N; i++) {
            permNum[i] = i;
        }
        
        // next_permutation을 통해 모든 경우의 수를 구해준다
        do {
            int bar = k; // 체력을 전역변수에서 관리함.(매 경우의 수마다 k초기화)
            result = Math.max(result, calc(dungeons, bar));

        } while(nextPermutation());
        
        
        return result;
    }
    
    static int calc(int[][] dungeons, int bar) {
        int cnt = 0;
        for(int idx: permNum) {
            // 각각의 경우의 수마다 최소 경우 수인지
            if(bar < dungeons[idx][0]) break; // 입구컷
            bar -= dungeons[idx][1];
            cnt++;
        }

        return cnt;
    }
    
    static boolean nextPermutation() {
        int i = N-1;
        while(i > 0 && permNum[i-1] >= permNum[i]) {
            i--;
        }
        // 더 이상 진행 못할경우'
        if(i == 0) return false;
        
        // i-1과 바꿔줄 j를 찾아줌
        int j = N-1;
        while(permNum[i-1] >= permNum[j]) {
            j--;
        }
        
        swap(i-1, j);
        
        // 마지막으로 다시 오름차순 정렬
        int k = N-1;
        while(i < k) {
            swap(i, k);
            i++;
            k--;
        }
        return true;
        
    }
    
    static void swap(int i, int j) {
        int tmp = permNum[j];
        permNum[j] = permNum[i];
        permNum[i] = tmp;
    }
    
}