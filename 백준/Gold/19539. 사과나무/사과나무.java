import java.util.*;
import java.io.*;

public class Main {
    static boolean flag;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int oddCnt = 0;
        int evenCnt = 0;
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num % 2 == 1) oddCnt++; // 홀수이면
            else evenCnt++; // 짝수이면
            sum += num;
        }
        
        if(sum == 0) flag = true;
        else {
            int divided = sum / 3; // 실제 조합 내 홀, 짝 조합 개수
            int mod = sum % 3;
            if(mod == 0) {
                if(oddCnt > divided) flag = false;
                else flag = true;
            }
            // 합이 3으로 나누어지지 않는다면 => 무조건 false(모든 물뿌리개조합의 합은 3으로 이루어져있음)
            else {
                flag = false;
            }
        }
        
        if(flag) System.out.println("YES");
        else System.out.println("NO");

    }
}