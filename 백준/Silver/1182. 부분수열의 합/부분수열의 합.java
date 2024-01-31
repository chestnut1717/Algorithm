import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main
{
    static int N; // 정수 개수
    static int S; // 더한 값 대상
    static int[] arr; // 입력으로 들어오는 N개의 정수 저장 배열
    static int[] numbers; // 부분수열을 담는 배열
    static int result; // 합이 S가 되는 부분수열의 개수
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; // N으로 초기화
		
		// N개의 정수 입력 받음
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 조합 계산
		for(int r = 1; r <= N; r++) {
		    numbers = new int[r]; // 길이가 1~N개인 부분수열에 대해서 다른 배열을 가지므로 동적으로 크기 변환
		    combi(N, r, 0, 0);
		}
		System.out.println(result);
		// 종료
		bw.close();
	}
	
	// 조합 구현하는 재귀함수만들기
	static void combi(int n, int r, int cnt, int start) {
	    // 종료 조건
	    if (cnt == r) {
	        int sum = summation(cnt);
	        if (sum == S) { // 만약 합이 S와맞으면 더해줌
	            result++;
	        }
	        return;
	    }
	    // 조합 구하기
	    for(int i = start; i < N; i++) {
	        numbers[cnt] = arr[i];
	        combi(n, r, cnt+1, i+1); // 실수 많이 하는 부분, start대신 i를 넣어줘야 한다.
	    }
	    
	}
	
	// 합을 구하는 메서드
	static int summation(int cnt) {
	    int sum = 0;
	    for(int i = 0; i < cnt; i++) {
	        sum += numbers[i];
	    }
	    return sum;
	}
}
