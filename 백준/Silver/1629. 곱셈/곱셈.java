import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		System.out.println(recur(A, B, C));
		// (A x B) / c = (AmodC) mod (BmodC)
	}
	static long recur(long A, long B, long C) {
	    if(B == 1) {
	        return A % C;
	    }
	    long temp = recur(A, B/2, C) % C;
	    if (B % 2 == 0) return (temp * temp) % C;
	    else return (temp * recur(A, B/2+1, C)) % C;
	}
}
