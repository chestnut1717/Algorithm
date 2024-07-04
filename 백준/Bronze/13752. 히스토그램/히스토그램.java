import java.util.*;
import java.io.*;

public class Main
{
    static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
		    int h = Integer.parseInt(br.readLine());
		    for(int j = 0; j < h; j++) {
		        System.out.print("=");
		    }
		    System.out.println();
		}
	}
}