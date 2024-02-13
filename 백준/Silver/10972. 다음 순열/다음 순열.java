import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		arr = new int[N]; // arr 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = nextPermutation(); // 다음 순열이 있는지 없는지 체크하는 용도
		if(flag) {
		    for(int i = 0; i < arr.length; i++) {
    		    System.out.printf("%d ", arr[i]);
    		}
    		System.out.println();
		} else {
		    System.out.println(-1);
		}

 
		
	}
	
	// 다음 순열 체크 용도
	static boolean nextPermutation() {
	    int idx = N-1;
	    // 1. 내림차순에서 갑자기 깎인 부분 찾기!
	    while (idx > 0 && arr[idx-1] >= arr[idx]) idx--;
	    
	    if(idx == 0) return false; // 완전히 내림차순이면 => 종료!
	    
	    // 2. 이제 꺾인 부분과 다른 부분 찾기!
	    int j = N-1;
	    while(arr[idx-1] >= arr[j]) j--;
	    
	    // 3. swap
	    swap(idx-1, j);
	    
	    // 4. 남은 부분들 다 오름차순
	    int k = N-1;
	    while(idx < k) {
	        swap(idx, k);
	        idx++;
	        k--;
	    }
	    return true;
	    
	}
	// 원소 swap
	static void swap(int idx, int pivot) {
	    int tmp = arr[idx];
	    arr[idx] = arr[pivot];
	    arr[pivot] = tmp;
	}
}