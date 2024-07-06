import java.util.*;
import java.io.*;

public class Solution {
    static int T; // 테스트 케이스 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        // 각각의 테스트 케이스마다
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 첫 번째 문자열 입력 받기
            st = new StringTokenizer(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < N; j++) {
                String a = st.nextToken();
                map.put(a, 1);
            }

            // 두 번째 문자열 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                String b = st.nextToken();
                if (map.containsKey(b)) {
                    map.put(b, 2);
                } else {
                    map.put(b, 1);
                }
            }

            // 결과 계산
            int result = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 2) {
                    result += 1;
                }
            }

            // 결과 출력
            sb.append(String.format("#%d %d\n", i + 1, result));

        }
        
        bw.write(sb.toString());
        bw.close();
    }
}
