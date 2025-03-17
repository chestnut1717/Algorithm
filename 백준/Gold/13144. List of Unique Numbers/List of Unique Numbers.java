import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[] arr = new int[100004];
    static int[] cnt = new int[100004];
    static long result; // 경우의 수 혹시 모르니깐

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		while(end < N) {
		    if(cnt[arr[end]] == 0) {
		        cnt[arr[end]]++;
		        end++;
		    } else {
		        result += (end - start);
		        cnt[arr[start]]--;
		        start++;
		    }
		}
		result += (long) (end - start) * (end -start + 1) / 2;
		
		System.out.println(result);
	}
	
}