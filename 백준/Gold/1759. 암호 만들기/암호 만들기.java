import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 1759 암호 만들기
 * - 소요시간 : 1시간 
 * - 난이도 : 상
 * - 아이디어 : 완전탐색을 dfs를 통해서 하고, 만들어진 조합이 모음 1개, 자음 2개를 만족할 경우에만 출력하는 방식을 쓴다.
 * - 완탐을 해도 최대 15C7 * 15(문자열 길이) 정도 이므로 충분히 해결
 */
public class Main {
    static int L, C;
    static char[] code;
    static char[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        code = new char[L];
        arr = new char[C];
        
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(arr); // 사전순 정렬
        
        dfs(0, 0);
    }
    
    static void dfs(int depth, int start) {
        if (depth == L) {
            if (isValid(code)) {
                for(char c: code){
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }
        
        for(int i = start; i < C; i++) {
            code[depth] = arr[i];
            dfs(depth+1, i+1);
        }
    }
    
    // 코드의 유효성 검증
    public static boolean isValid(char[] code) {
        int c = 0;
        int v = 0;
        for(int i = 0; i < code.length; i++) {
            if (code[i] == 'a' || code[i] == 'e' || code[i] == 'i' || code[i] == 'o' || code[i] == 'u' ) {
                v++;
            } else {
                c++;
            }
        }
        if (c >= 2 && v >= 1) {
            return true;
        } else {
            return false;
        }
    }
}