import java.util.*;
import java.io.*;

public class Main
{
    static int T; // 비교 수
    static int N; // 배열 A 개수
    static int M; // 배열 B 개수
    static int[] arrA;
    static int[] arrB;
    static int[] sumA;
    static int[] sumB;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		arrA = new int[N];
		
		// A 배열 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		arrB = new int[M];
		// 배열 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
		    arrB[i] = Integer.parseInt(st.nextToken());
		}
		
		// 우선 모든 부분합을 구해준다(O(n^2))
		sumA = new int[N*(N+1)/2];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
		    int temp = 0;
		    for(int j = i; j < N; j++) {
		        temp += arrA[j];
		        sumA[cnt++] = temp;
		      //  System.out.printf("%d, %d, %d, %d\n", cnt, i, j, temp+arrA[j]);
		    }
		}
		
		sumB = new int[M*(M+1)/2];
		cnt = 0;
		for(int i = 0; i < M; i++) {
		    int temp = 0;
		    for(int j = i; j < M; j++) {
		        temp += arrB[j];
		        sumB[cnt++] = temp;
		    }
		}
		
		// 그리고 A는 오름차순, B는 내림차순으로 정렬을 해줘서 two pointer을 할 수 있도록 해주자.
		Arrays.sort(sumA);
		Arrays.sort(sumB); // sumB는 뒤에서부타 탐색하자.
		
	    int pivotA = 0;
	    int pivotB = sumB.length-1;

	    long result = 0;
	    while(pivotA < sumA.length && pivotB >= 0) {
	        int currA = sumA[pivotA];
	        int currB = sumB[pivotB];
	        // 우선 부분합이 T보다 클 경우 => pivotB를 감소시키면서 상황을 파악한다.
	        if(T < currA + currB) {
	            pivotB--;
	        }
	        // 값이 동일하다면 중복되는 것을 다 count해야 한다.
	        else if(T == currA + currB) {
	            long cntA = 0;
	            long cntB = 0;
	            while(pivotA < sumA.length && currA == sumA[pivotA]) {
	                cntA++;
	                pivotA++;
	            }
	            while(pivotB >= 0 && currB == sumB[pivotB]) {
	                cntB++;
	                pivotB--;
	            }
	            
	            result += (cntA * cntB);
	            

	        }
	        // 더 작다면 => pivotA 증가
	        else {
	            pivotA++;
	            
	        }
	        
	    }
	    
	    System.out.println(result);

	}
}