import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int N = Integer.parseInt(br.readLine());

	    int flag = 0; // 제곱수 여부 확인
	    int bit = 1;
		for(int i = 0; i <= 30; i++) {
		    if (N == bit) {
		        flag = 1;
		        break;
		    }
		    bit = bit << 1;
		}
		
		System.out.println(flag);
	}
}