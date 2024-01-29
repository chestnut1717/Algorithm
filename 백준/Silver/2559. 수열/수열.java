import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main
{
    static int N;
    static int K;
    static int[] arr;
    static int[] accSum;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split("\\s");
		N = Integer.parseInt(tmp[0]);
		K = Integer.parseInt(tmp[1]);
		
		arr = new int[N];
		accSum = new int[N+1];
		
	    String[] tmp2 = br.readLine().split("\\s");
	    for(int i = 0; i < N; i++) {
	        arr[i] = Integer.parseInt(tmp2[i]);
	        accSum[i+1] = accSum[i] + arr[i];
	        
	    }
	    
	    int maximum = -100000000;
	    for(int i = K; i < N + 1; i++) {
	        maximum = Math.max(maximum, accSum[i] - accSum[i-K]);
	    }
	    System.out.println(maximum);

		
	}
}
