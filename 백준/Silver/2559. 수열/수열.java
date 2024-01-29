import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main
{
    static int N;
    static int K;
    static int[] accSum;
    
	public static void main(String[] args) throws IOException {
	    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer 활용해서 split
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		accSum = new int[N+1]; // 누적합을 하기 위해 N + 1개의 배열 만듦
		
		st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < N; i++) {
	        accSum[i+1] = accSum[i] + Integer.parseInt(st.nextToken()); // acc의 처음 값은 0이기 때문에, 누적값 가능
	        
	    }
	    
	    int maximum = -100000000;
	    
	    // K개의 연속된 값의 합은, 누적합끼리의 차를 구하는 것과 동일
	    for(int i = K; i < N + 1; i++) {
	        maximum = Math.max(maximum, accSum[i] - accSum[i-K]);
	    }
	    System.out.println(maximum);

		
	}
}
