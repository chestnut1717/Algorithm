import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<List<Integer>> lst = new ArrayList<>();
    static Stack<List<Integer>> stk = new Stack<>();
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine()); // 탑 개수 입력
        StringTokenizer st = new StringTokenizer(br.readLine()); // 탑의 높이 입력
        for(int i = 0; i < N; i++) {
            lst.add( Arrays.asList(Integer.parseInt(st.nextToken()), i+1) ); // 높이와 탑의 번호
            
        }
        // N 초기화
        arr = new int[N];
        
        // 스택에 넣을 때
        for(int i = 0; i < N; i++) {
            while(!stk.empty()) {
                if(lst.get(i).get(0) <= stk.peek().get(0)) { // 현재 값과 스택 높이 비교해서
                    arr[i] = stk.peek().get(1); // 스택이 더 크다면, 스택의 위치 저장
                    break;
                }
                else { // 그렇지 않을 경우, 스택의 값을 빼준다.
                    stk.pop();
                }
            }
            if(stk.empty()) {
                arr[i] = 0;
            }
            
            stk.push(lst.get(i));
            
        }
        for(int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }


        bw.write(sb.toString());
        bw.close();
    }
}