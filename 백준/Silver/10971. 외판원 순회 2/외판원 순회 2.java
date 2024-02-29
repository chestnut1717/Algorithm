import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] numbers; // 배열 상태 담을 것(idx = 도시번호)
    static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		numbers = new int[N];
		// 초기 상태의 배열 채워주기!
		for(int i = 0; i < N; i++) {
		    numbers[i] = i;
		}
		
		// 배열 채워넣기
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		do {
		    // 해당 순서에서 나올 수 있는 거리의 합
		    int tmpMin = 0;
		    int start = numbers[0];
		    boolean flag = true; // 갈 수 있느냐 없느냐 확인용
		    
		    // numbers배열의 순서에 대해 최솟값 찾음 ([1,2,3,4] = 1 -> 2 -> 3 -> 4)
		    for(int i = 0; i < N-1; i++) {
		        int prev = numbers[i];
		        int next = numbers[i+1];
		        int dist = arr[prev][next];
		        
		        // 갈 수 없는 경우
		        if(dist == 0) {
		            flag = false;
		            break;
		        }
		        tmpMin += dist;
		    }
		    if(flag == true) {
		        // 마지막 순회 (ex. 4 -> 1)
		        int dist = arr[numbers[N-1]][start];
		        // 마지막 순회에도 0이면 무시
		        if (dist == 0) {
		            flag = false;
		            continue;
		        }
		        tmpMin += dist;
    		    // min 갱신
    		    min = Math.min(tmpMin, min);
		    }
		    
		    
		} while(nextPermutation());
		
		System.out.println(min);
	}
	
	
	// 그 다음 permutation을 채워줄 배열!
	static boolean nextPermutation() {
	    int i = N-1;
	    
	    // 봉우리 아래를 만날 때까지
	    while(i > 0 && numbers[i-1] > numbers[i]) {
	        i--;
	    }
	    
	    // 이미 내림차순이므로
	    if(i == 0) {
	        return false;
	    }
	    
	    int j = N-1;
	    
	    //  그 다음 i-1과 바꿔줄 j찾기
	    while(numbers[i-1] > numbers[j]) j--;
	    swap(i-1, j);
	    
	    // 그 후 다시 정렬해주기
	    int k = N-1;
	    while(i < k) {
	        swap(i, k);
	        i++;
	        k--;
	    }
	    return true;
	}
	
	// 두 원소 바꿔주는 코드
	static void swap(int i, int j) {
	    int tmp = numbers[i];
	    numbers[i] = numbers[j];
	    numbers[j] = tmp;
	}
}
