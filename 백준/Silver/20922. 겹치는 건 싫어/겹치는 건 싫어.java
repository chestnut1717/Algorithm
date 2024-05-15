import java.util.*;
import java.io.*;

public class Main
{
    static int N, K;
    static int[] arr;
    static int[] count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		count = new int[100001]; // 최대 100,000이하의 양의정수이므로!
		// 숫자 입력
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		int start = 0;
		int end = 0;
		while(end < N) {
		    // 우선 K개 한계가 걸릴 때까지 지속한다.

		    while(end < N && count[arr[end]] + 1 <= K) {
		        count[arr[end]]++;
		        end++;
		    }
		    
		    answer = Math.max(answer, end - start);
		    count[arr[start]]--;
		    start++;
		    
		    
		}
		System.out.println(answer);
	}
}
