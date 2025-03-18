import java.util.*;
import java.io.*;
public class Main
{
    static int N;
    static int X;
    static int[] arr;
    static long result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 배열 초기화
		arr = new int[N];
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 정렬
		Arrays.sort(arr);
		
		X = Integer.parseInt(br.readLine());

		int start = 0;
		int end = N-1;
		
		// 1개 처리
		if(N == 1 && arr[0] == X) {
		    System.out.println(1);
		    return;
		}
		
		while(start < end) {
		    int tmp = arr[start] + arr[end];
		     // 동일하다면 result 더해주기
		     if(tmp == X) {
		         // 처음 및 끝값이 다를 때
		         if (arr[start] == arr[end]) {
		             result += (long) (end - start) * (end - start + 1) / 2;
		             break;
		         }
		         // 그렇지 않다면
		         start++;
		         long cnt1 = 1;
		         long cnt2 = 1;
		         // 같은 것이 나오지 않을 때까지 추가한다.
		         while(start < end && arr[start-1] == arr[start]) {
		             start++;
		             cnt1++;
		         }
		         while (start < end && arr[end-1] == arr[end]) {
		             end--;
		             cnt2++;
		         }

		         result += (cnt1 * cnt2);
		         
		     } else if (tmp > X) {
		         end--;
		     } else {
		         start++;
		     }
		}
		
		System.out.println(result);
		
		
	}
}