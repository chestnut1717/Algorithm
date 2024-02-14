import java.util.*;
import java.io.*;

public class Main {
    static int[] left = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] right = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    static int[][] possible = new int[6][3];
    static int[][] result = new int[6][3];
    static int[] answer = {0, 0, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int x = 0; x < 4; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 3; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken()); // 입력으로 들어온 승무패 저장!
                }
            }
            
            rec(0, x);
            sb.append(answer[x]).append(" ");
        }
        System.out.println(sb);
    }
    
    static void rec(int depth, int tc) {
        if(depth == 15) {
            boolean equal = true;
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 3; j++) {
                    if(result[i][j] != possible[i][j]) {
                        equal = false;
                    }
                }
            }
            if (equal) {
                answer[tc] = 1;
            }
        }
        else {
            // left 승, right 패
            possible[left[depth]][0]++;
            possible[right[depth]][2]++;
            if(possible[left[depth]][0] <= result[left[depth]][0] && possible[right[depth]][2] <= result[right[depth]][2]) {
                rec(depth+1, tc);
            }
            // 원상복귀
            possible[left[depth]][0]--;
            possible[right[depth]][2]--;
            // 무승부
            possible[left[depth]][1]++;
            possible[right[depth]][1]++;
            // 이번 계산에서 입력값보다 커진 경우, 이후 진행이 무의미
            if(possible[left[depth]][1] <= result[left[depth]][1] && possible[right[depth]][1] <= result[right[depth]][1]) {
                rec(depth+1, tc);
            }
            // 원상복귀
            possible[left[depth]][1]--;
            possible[right[depth]][1]--;
            // left 지고, right 이기는 경우
            possible[left[depth]][2]++;
            possible[right[depth]][0]++;
            if(possible[left[depth]][2] <= result[left[depth]][2] && possible[right[depth]][0] <= result[right[depth]][0]) {
                rec(depth+1, tc);
            }
            possible[left[depth]][2]--;
            possible[right[depth]][0]--;
        }
    }
}