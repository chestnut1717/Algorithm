import java.util.*;
import java.io.*;

// 11:39
public class Main
{
    static int N, K;
    static int[] number;
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
	    int top = -1; // 스택처럼 활용하기 위해 배열 중 top 가리키는 idx
	    int cnt = K;
	    for(int i = 0; i < N; i++) {
	        int num = tmpStr.charAt(i) - '0';
	        while(cnt > 0 && top > -1 && number[top] < num) {
	            number[top] = -1;
	            top--;
	            cnt--;
	        }
	        top++;
	        number[top] = num;
	    }
	    

        for(int i = 0; i < N-K; i++) {
            sb.append(number[i]);
        }
	    
	    
	    
	    bw.write(sb.toString());
	    bw.close();
		
	}
}