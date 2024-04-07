import java.util.*;
import java.io.*;

// 5:00
public class Main
{
    static int N;
    static int[] arr;
    static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		// 이제 next Permutation 진행해보잗.
		do {
		    // 해당 조합을 가지고 계산해본다.
		    max = Math.max(max, calc());
		    
		} while (nextPermutation());
		
		System.out.println(max);
		
	}
	static int calc() {
	    int sum = 0;
	    for(int i = 0; i < N-1; i++) {
	        sum += Math.abs(arr[i] - arr[i+1]);
	    }
	    return sum;
	}
	
	static boolean nextPermutation() {
	    int i = N-1;
	    
	    // 우선, 꼭대기에서 꺾인 부분을 찾아준다.
	    while(i > 0 && arr[i-1] >= arr[i]) {
	        i--;
	    }
	    if(i == 0) return false;
	    
	    // 그리고, 꺾인 부분이랑 교체할 j 찾아주기
	    int j = N-1;
	    while(arr[i-1] >= arr[j]) j--;
	    
	    // 그리고 두개를 교환해주낟.
	    swap(i-1, j);
	    
	    // 나머지 정렬해주기.
	    int k = N-1;
	    while(i<k) {
	        swap(i++, k--);
	    }
	    
	    return true;
	}
	
	static void swap(int i, int j) {
	    int tmp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = tmp;
	}
}