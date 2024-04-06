import java.io.*;
import java.util.*;

public class Main
{
    static int N; // 수열 a의 크기
    static int[] arr;
    static int[] ret;
    static Stack<Integer> stk = new Stack<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		ret = new int[N+1];
		
		Arrays.fill(ret, -1);
		
		// 우선 arr에 저장해 둠
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		    while(stk.size() > 0 && arr[stk.peek()] < arr[i]) {
		        ret[stk.pop()] = arr[i];
		    }
		    stk.push(i);
		}
		
		
		for(int i = 1; i <= N; i++) {
		    sb.append(ret[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
}
