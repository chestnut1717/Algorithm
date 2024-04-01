import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] lis;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		// lis를 활용해 풀어준다.
		lis = new int[N];
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
		    lis[i] = 1;
		    for(int j = 0; j < i; j++) {
		        if(arr[j] < arr[i]) {
		            lis[i] = Math.max(lis[i], lis[j] + 1); 
		        }
		        
		    }
		    max = Math.max(max, lis[i]);
		}
		
		// 최소로 움직여야 할 값은 N에서 현재 LIS의 길이를 빼줘야 함
		System.out.println(N - max);
	}
}
