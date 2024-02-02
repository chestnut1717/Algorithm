import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int pivot;
    static int[] votes;
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 후보자 입력
		N = Integer.parseInt(br.readLine());
		
		// 2.1 단일 후보일 때
		if (N == 1) {
		    result = 0;
		    
		// 2.2 후보가 2명 이상일 때
		} else {
    		votes = new int[N-1]; // 자기 말고 다른 후보자들만 저장하도록 N-1로 초기화
    		for(int i = 0; i < N; i++) {
    		    if (i > 0) {
    		        votes[i-1] = Integer.parseInt(br.readLine()); // 다른 후보자들 입력(0번째 인덱스부터 해야 함)
    		    } else {
    		        pivot = Integer.parseInt(br.readLine()); // 자신의 값 저장
    		    }
    		}
    		// 정렬 inplace
    		Arrays.sort(votes);

    		// 검사
    		while(pivot <= votes[N-2]) { // 자신과 가장 뒤에 있는(큰 원소가 같다면
    		    result++;
    		    pivot++;
    		    votes[N-2]--;
    		    Arrays.sort(votes); // 다시 재정렬한다.(복수의 최댓값이 존재할 수 있으므로)
    		}
		}
		
		// 결과 출력
		System.out.println(result);
	
	}
}

