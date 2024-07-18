import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    static long N;
    static long[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		arr = new long[64];
		// 각각의 거듭제곱을 채워준다.
		arr[0] = 1;
		int pivot = ((N & (N - 1)) == 0) ? (int) (Math.log(N) / Math.log(2)) -1 : (int) (Math.log(N) / Math.log(2));
		
		for(int i = 1; i <= pivot+1; i++) {
		    arr[i] = arr[i-1] << 1;
		}
		

		System.out.println(thueMorse(N, pivot));

		
	}
	
	static int thueMorse(long n, int pivot) {
	    if (n == 1L || n == 0L) return 0;

        long nextN = (n - arr[pivot] == 0) ? n - arr[pivot-1] : n - arr[pivot];
        int nextPivot = (int) (Math.log(nextN) / Math.log(2));
	    return 1 - thueMorse(nextN, nextPivot);
	}
}