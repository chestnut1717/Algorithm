import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] charArr = br.readLine().toCharArray();
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(charArr[N-1]);
		
	}
}