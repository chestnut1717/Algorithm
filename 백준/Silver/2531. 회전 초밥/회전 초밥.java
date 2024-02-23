import java.util.*;
import java.io.*;

public class Main {
    static int N, d, k, c;
    static int sushi[]; // 현재 벨트에 있는 초밥
    static int menu[]; // 가짓수 메뉴판
    static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken()); // 초밥 접시 수
        d = Integer.parseInt(st.nextToken()); // 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰번호

        sushi = new int[N];
        menu = new int[d + 1]; // 메뉴 순번은 1부터 시작하므로 초기화
        menu[c] = 1; // 항상 고정

        // 접시 수 가져오기
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        boolean flag = false;
        // 1. 연속해서 먹는 경우의 수를 구해보자!
        int prev = 0; // 이전 메뉴가 담김!
        int next = 0; // 다음 메뉴가 담김!
        int tmpResult = 1;
        menu[c]++;
        
        // 2. 각각의 strike 구함
        Queue<Integer> dq = new ArrayDeque();

        for(int i = 0; i < k; i++) {
            dq.offer(sushi[i]);
            // 덱에 있는 스시 계산
            menu[sushi[i]]++;
            if(menu[sushi[i]] == 1) tmpResult++;
        }
        
        result = Math.max(result, tmpResult);
        
        for(int i = k; i < N + k - 1; i++) {
            // 직전의 값 제거해준다.
            prev = dq.poll();
            menu[prev]--;
            if(menu[prev] == 0) tmpResult--;
            
            next = sushi[i % N];
            menu[next]++;
            if(menu[next] == 1) tmpResult++;
            dq.offer(next);
            
            result = Math.max(result, tmpResult);
        }    


        System.out.println(result);

    }

}