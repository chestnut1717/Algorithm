import java.util.*;
import java.io.*;

public class Main {
    static int S, P;
    static char[] input; // 입력
    static int[] arr = new int[4]; // 제약조건
    static int[] moving = new int[4]; // 움직이면서 check
    static long result;

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        //문자열 입력받기
        input = br.readLine().toCharArray();

        
        // 제약 조건 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        
        // 초기 세팅
        for(int i = 0; i < P; i++) {
            if(input[i] == 'A') moving[0]++;
            else if(input[i] == 'C') moving[1]++;
            else if(input[i] == 'G') moving[2]++;
            else if(input[i] == 'T') moving[3]++;
        }
        if(isValid()) result++; // 초기 세팅할 때도 result 갱신해 줘야 한다.

        // 슬라이딩 윈도우
        for(int i = 1; i <= S-P; i++) { // 0번째는 처리 했으므로 S-P+1번 반복
            
            // 다음 window 추가
            if(input[i+P-1] == 'A') moving[0]++;
            else if(input[i+P-1] == 'C') moving[1]++;
            else if(input[i+P-1] == 'G') moving[2]++;
            else if(input[i+P-1] == 'T') moving[3]++;
            
            // 이전 window 삭제
            if(input[i-1] == 'A') moving[0]--;
            else if(input[i-1] == 'C') moving[1]--;
            else if(input[i-1] == 'G') moving[2]--;
            else if(input[i-1] == 'T') moving[3]--;

            // 비교
            if(isValid()) result++;
        }
        System.out.println(result);
        

        
    }
    static boolean isValid() {
        for(int i = 0; i < 4; i++) {
            if(arr[i] > moving[i]) {
                return false;
            }
        }
        return true;
    }
    
}