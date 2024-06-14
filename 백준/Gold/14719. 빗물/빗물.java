import java.util.*;
import java.io.*;

public class Main
{
    static int H, W;
    static int[] arr;
    static Stack<Integer> stk = new Stack<>(); // 벽을 저장하는 배열
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		// W만큼 블록 들어옴
		arr = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
        
	    int result = 0;
	    
	    // 첫, 마지막 빗물 제외
	    for(int i = 1; i < W-1; i++) {
	        int left = 0;
	        int right = 0;
	        
	        // 현재 위치보다 왼쪽에서 가장 높은 블럭을 찾기
	        for(int j = 0; j < i; j++) {
	            left = Math.max(arr[j], left);
	        }
	        // 현재 위치보다 오른쪼에서 가장 높은 블럭을 찾기
	        for(int j = i+1; j < W; j++) {
	            right = Math.max(arr[j], right);
	        }
	        // 둘 중에 하나라도 만족하지 않는다면 => 넘칠 수밖에 없다.
	        if(arr[i] < left && arr[i] < right) {
	            // 둘 중 작은 블럭을 기준으로 채워주기
	            result += Math.min(left, right) - arr[i];
	        }
	    }
	    
	    System.out.println(result);
		
	}
}
