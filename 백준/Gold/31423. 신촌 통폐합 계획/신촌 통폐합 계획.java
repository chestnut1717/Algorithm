import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static String[] schools;
    static int[] next;
    static int[] tail;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 학교 이름
        schools = new String[N + 1];
        next = new int[N + 1];
        tail = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            schools[i] = br.readLine();
            next[i] = i;
			tail[i] = i;
        }

        // N-1개 입력 받기
        int cur = -1;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int addSchool = Integer.parseInt(st.nextToken());
            int removeSchool = Integer.parseInt(st.nextToken());

            // 연결을 갱신
            next[tail[addSchool]] = removeSchool;
            tail[addSchool] = tail[removeSchool];
            cur = addSchool;
        }


        // 연결 리스트 순회하며 문자열 구성
        for (int i = 0; i < N; i++) {
			sb.append(schools[cur]);
			cur = next[cur];
		}

        bw.write(sb.toString());
        bw.close();
    }


}
