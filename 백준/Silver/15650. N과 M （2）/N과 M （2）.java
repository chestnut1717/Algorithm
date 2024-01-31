import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @since jdk1.8
 * - 문제 : swea 1210 - 농작물 수확하기
 * - 난이도 : 하
 * - 소요시간 : 20분
 * - 아이디어 : 재귀를 활용해서 조합을 구함.
 *   1. 중요한 점은 start와 count를 잘 활용하여 따로 방문 여부를 체크하는 visited 배열을 만들지 않아도 괜찮다.
 */
public class Main
{
    static int n; // n범위
    static int r; // 수열 길이
    static int[] arr; // 1~n 숫자 저장하는 공간
    static int[] numbers; // 수열 저장하는 공간
    static StringBuilder sb = new StringBuilder(); // 전역적으로 StringBuilder 사용하기 위해서
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 자연수 n 입력
		r = Integer.parseInt(st.nextToken()); // 수열 길이 입력
		
		arr = new int[n+1]; // 배열 초기화
		
		
		for(int i = 1; i <= n; i++) { // 숫자 저장하는 공간에 1~N까지 모두 저장
		    arr[i] = i;
		}
		numbers = new int[r]; // 크기 r로 초기화(길이가 r이므로)
		
		combi(n, r, 0, 1); // 조합 모든 경우 구하기
		
		System.out.println(sb.toString()); //저장된 수열 경우의 수 모두 출력
	}
	
	// 재귀를 통해 조합 구함
	static void combi(int n, int r, int count, int start) {
	    if(count == r) {
	        save(); // 종료 조건에 도달했으면, 수열을 저장함
	        return;
	    }
	    for(int i = start; i <= n; i++) {
	        numbers[count] = arr[i];
	        combi(n, r, count+1, i+1); // 중복조합이 아닌 그냥 조합이므로 이전의 경우가 나오면 안된다.
	    }
	}
	
	// StringBuilder을 활용해 수열 저장 코드
	static void save() {
	    for(int i = 0; i < r; i++) {
	        sb.append(numbers[i]).append(" ");
	    }
	    sb.append("\n");
	}
}
