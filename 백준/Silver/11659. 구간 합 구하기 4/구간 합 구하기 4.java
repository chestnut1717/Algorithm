import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    static int N;
    static int M;
    static int[] arr; // 누적합을 사용하기 위함
    static String[] tmp;
    static int start;
    static int end;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1]; // 담을 배열 초기화
		
		// N개의 수 주어짐
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
		    arr[i] = arr[i-1] + Integer.parseInt(st2.nextToken()); // 누적합 활용
		}
		
		StringBuilder sb = new StringBuilder(); // 출력을 위함
		
		/* M개의 값 입력받아서 누적합의 원리를 통해 출력 */
		for(int i = 0; i < M; i++) {
		    tmp = br.readLine().split("\\s");
		    start = Integer.parseInt(tmp[0]);
		    end = Integer.parseInt(tmp[1]);
		    
		    sb.append(arr[end] - arr[start-1]).append("\n");
		}
		
		// 출력
		System.out.println(sb.toString());
	}
}
