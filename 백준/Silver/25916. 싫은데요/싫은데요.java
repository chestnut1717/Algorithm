import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    static int[] arr; // 10억 * 500000
    static int result = 0; // A[i]가 10억
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		// N개의 구멍 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이제 누적합을 통해 진행한다.
        int left = 0;
        int right = 0;
        int sum = 0; // 더할 수 있는 최댓값도 어차피 20억 이하임
        while(right < N) {
            if(sum + arr[right] <= M) {
                sum += arr[right];
                result = Math.max(sum ,result);
                right++;
            } else {
                sum -= arr[left];
                left++;
            }
        }
        
        
        
        System.out.println(result);

		
	}
}