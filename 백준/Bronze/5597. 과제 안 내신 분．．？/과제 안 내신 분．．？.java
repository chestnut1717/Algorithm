import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] arr = new boolean[31];
		
		for(int i = 0; i < 28; i++) {
		    int idx = Integer.parseInt(br.readLine());
		    arr[idx] = true;
		}
		
		for(int i = 1; i < arr.length; i++) {
		    if(arr[i] != true) System.out.println(i);
		}
	}
}