import java.io.*;
import java.util.*;

public class Main
{
    static int N; // 입력 숫자 개수
    static int[] arr;
    static int result = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 숫자 N 입력
		N = Integer.parseInt(br.readLine());
		
		// N개의 숫자 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬을 해준다.
		Arrays.sort(arr);
	    // 투포인터로 탐색해야 함
		for(int i = 0; i < N; i++) {
		    int left = 0;
		    int right = N-1;
		    
		    while(true) {
		        // 만약 left, right가 겹칠 경우는 무시(서로 다른 위치)
		        if(left == i) {
		            left++;
		        } else if(right == i) {
		            right--;
		        }
		        // 종료조건(left가 right보다 큰 경우)
		        if(left >= right) {
		            break;
		        }
		        // 두 수의 합이 찾고자 하는 값보다 작을 경우
		        if(arr[left] + arr[right] < arr[i]) {
		            left++;
		        } else if(arr[left] + arr[right] > arr[i]) {
		            right--;
		        } else {
		            result++;
		            break;
		        }
		         
		    }
		    
		}
		

		// 정답 출력
		System.out.println(result);
		
		
	}
}