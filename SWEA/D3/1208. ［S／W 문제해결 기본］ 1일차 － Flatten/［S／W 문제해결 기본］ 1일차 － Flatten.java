import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @since jdk1.8
 * - 문제 : swea 1208 - Flatten
 * - 메모리 : 19,608 kb / 시간 : 134 ms
 * - 소요시간 : 1시간
 * - 아이디어 : 반복문을 활용한 완전 탐색(가로 길이 = 100, 최대 반복 횟수 = 1000, 테스트케이스 = 10)
 *   1. 평탄화 작업이 끝난 후 => 반복문을 다시 돌려서 최댓값과 최솟값을 갱신해야 함
 *   2. 최솟값이 여러 개 있는 경우의 수가 있기 때문 
 */

public class Solution
{

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for(int test_case = 1; test_case <= 10; test_case++)
		{
		    int N = Integer.parseInt(br.readLine());
		    int[] arr = new int[100]; // 배열 초기화
		    
		    
		    // 가로 100개 입력받음(항상 100)
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int i = 0; i < 100; i++) {
		        arr[i] = Integer.parseInt(st.nextToken());
		    }
		    


		    int result = 0;
		    // 지속적으로 갱신(n번 덤프)
		    for(int i = 0; i < N; i++) {
		    	int maximum = arr[0];
		    	int minimum = arr[0];
			    int maxIdx = 0;
			    int minIdx = 0;
			    
		        // 최고, 최저값 찾기
    		    for(int j = 1; j < 100; j++) {
    		        if (arr[j] > maximum) {
    		            maximum = arr[j];
    		            maxIdx = j;
    		        }
    		        if (arr[j] < minimum) {
    		            minimum = arr[j];
    		            minIdx = j;
    		        }
    		    }
    		    

    		    // 갱신하기
    		    arr[minIdx]+= 1;
    		    arr[maxIdx]-= 1;
    		    
    		    // 최고, 최저값 찾기
    		    maximum = arr[0];
    		    minimum = arr[0];
		        
                // 갱신 후 최대, 최솟값 다시 찾기
    		    for(int j = 1; j < 100; j++) {
    		        if (arr[j] > maximum) {
    		            maximum = arr[j];
    		            maxIdx = j;
    		        }
    		        if (arr[j] < minimum) {
    		            minimum = arr[j];
    		            minIdx = j;
    		        }
    		    }
    		    // 종료조건
    		    if(maximum - minimum <= 1) {
    		    	result = maximum - minimum;
    		    	break;
    		    }
    		    
    		    // 완료한 다음에 최고점과 최저점 높이 차 확인
    		    if(i == N-1) {
    		        result = maximum - minimum;
    		        break;
    		    }
 		    
    		    
		    }
		    
		System.out.printf("#%d %d%n",test_case, result);
		}
	}
}
