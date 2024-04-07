import java.util.*;
import java.io.*;

// 11:39
public class Main
{
    static int N, K;
    static int[] number;
    static Stack<Integer> stk = new Stack<>();
	public static void main(String[] args) throws Exception{
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    StringBuilder sb = new StringBuilder();
	    
	    N = Integer.parseInt(st.nextToken()); 
	    K = Integer.parseInt(st.nextToken()); 
	    
	    // 길이 N의 숫자 입력
	    number = new int[N];
	    String tmpStr = br.readLine();
	    for(int i = 0; i < N; i++) {
	        number[i] = tmpStr.charAt(i) - '0';
	    }
	    
	    
	    // 이제 각각의 n을 탐색하면서
	    for(int num: number) {
	        // 다음에 들어오는 것이 스택의 값보다 클 때 => 지워저야함
	        while(K > 0 && !stk.isEmpty() && stk.peek() < num) {
	            K--;
	            stk.pop();
	        }
	        stk.push(num);
	    }
	    
	    // K개 남은 것들을 처리해 줘야 함 => 이때는 뒤에것부터 빼줘도 무방
	    // 항상 비내림차순으로 들어간 게 보장되므로
	    while(K > 0) {
	        stk.pop();
	        K--;
	    }
	    
	    for(int num: stk) {
	        sb.append(num);
	    }
	    
	    
	    
	    bw.write(sb.toString());
	    bw.close();
		
	}
}
