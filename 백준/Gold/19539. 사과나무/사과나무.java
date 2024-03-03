import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * - 문제 : BJ 19539 사과나무
 * - 소요시간 : 1시간 
 * - 난이도 : 상
 * - 아이디어 : 전형적인 그리디 문제 - 어떻게 물을 뿌려야 할지보다는 가능 여부만 나타내면 된다.
 * - 물뿌리개 조합은 (0, 3), (3, 0), (1, 2), (2, 1)가 나온다. 이 조합을 이용해서 그리디 방식으로 품
 * - 우선 사과나무 높이들 중에서 홀수, 짝수, 나무의 합을 셈
 * - 1. 만약 합이 0이면 = true(처음 상태 그대로)
 * - 2. 합이 1 이상이고, 합이 3으로 나누어지지 않는다면 => false(물뿌리개 조합은 3의 배수만 처리 가능)
 * - 3. 합이 1 이상이고, 합이 3으로 나누어지며, 홀수 개의 높이 개수가 물뿌리개 조합에서 홀수가 나올 수 있는 개수(sum / 3)보다 클 경우 => false
 * - 4. 합이 1 이상이고, 합이 3으로 나누어지며, 홀수 개의 높이 개수가 물뿌리개 조합에서 홀수가 나올 수 있는 개수(sum / 3)보다 작을 경우 => true
 *   - 3, 4가 성립되는 이유는, 물뿌리개 조합의 원소는 항상 홀수 1개, 짝수 1개로 이루어져있기 때문이다.(0도 짝수)
 *   - 따라서 1, 1, 1, 3, 3과 같이 물뿌리개가 처리할 수 있는 경우보다 홀수가 더 많을 경우는 무조건 false
 **/
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
        
        int divided = sum / 3;
        int mod = sum % 3;
        
        if(mod == 0 && oddCnt <= divided) flag = true;
        else flag = false;
        
        if(flag) System.out.println("YES");
        else System.out.println("NO");

    }
}