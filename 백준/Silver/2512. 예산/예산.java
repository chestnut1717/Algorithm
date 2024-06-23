import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 지방의 수
    static int[] arr; // 지방 개수
    static int M; // 총 예산 개수
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
    static long sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 지방 예산 담는 배열 초기화
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		    min = Math.min(min, arr[i]);
		    max = Math.max(max, arr[i]);
		    sum += arr[i];
		}
		// M 입력
		M = Integer.parseInt(br.readLine());

		// 우선 지방에산의 합이 국가예산의 상한선을 초과하지 않는다면
		if (M >= sum) {
		    System.out.println(max);// 바로 배정된 예산들 중 최댓값 출력
		    return;
		} else {
		    // 여기서는 이분탐색을 이용해 진행한다.
		    // 우선 min, max를 기준으로 mid를 만들어서 탐색해주고
		    // 만약 min > max가 발생하면 바로 mid를 반환
		    // 만약 min == max이면 min반환
		    int result = binarySearch(0, max);
		    System.out.println(result);
		}
	}
	static int binarySearch(int min, int max) {
	    // 우선 초과하는 일이 생기면
	    if(min>max) {
	        return max;
	    }
	    int mid = (min + max) / 2;
	    // 그리고 우선 arr을 기준으로 다시 탐색한다.
	    long tempSum = 0;
	    for(int i = 0; i < N; i++) {
	        tempSum += (arr[i] > mid) ? mid : arr[i];
	        if(tempSum > M) {
	            break;
	        }
	    }
	    // 그리고 초과했다면 => 범위를 더 줄여야 함
	    // 아직 더 갈 수 있다면(시작점 증가)
	    // 일치한다면 => 종료
	    if(tempSum > M) {
	        return binarySearch(min, mid-1);
	    } else if(tempSum < M){
	        return binarySearch(mid+1, max);
	    } else {
	        return mid;
	    }
	    
	}
}