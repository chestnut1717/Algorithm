import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;
    static int[] newArr;
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		newArr = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
        
        if (K >= N) {
            result = 0;
        } else {
            // 우선 센서 좌표가 담긴 배열을 오름차순으로 정렬한다.
    		Arrays.sort(arr);
    		for(int i = 0; i< N-1; i++) {
    		    newArr[i] = arr[i+1] - arr[i];
    		}
    		
    		// 각 센서의 차 배열을 다시 오름차순 해준다.
    		Arrays.sort(newArr);
    		
    		// 앞에서 N-K만큼 더한다.
    		for(int i = 0; i < N-K; i++) {
    		    result += newArr[i];
    		}
    		
        }
        
        // 출력
        System.out.println(result);

		
	}
}
