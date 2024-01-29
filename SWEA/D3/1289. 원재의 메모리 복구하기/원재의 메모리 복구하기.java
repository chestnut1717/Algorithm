import java.util.Scanner;

class Solution
{
    
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		sc.nextLine();

		for(int test_case = 1; test_case <= T; test_case++)
		{
		    
		    String memory = sc.nextLine();
		    int result = 0;
		    int initPos = memory.indexOf("1");
            // 1이 존재하지 않을 수도 있으므로 예외처리
		    if (initPos == -1) {
		        result = 0;
		    } else {
		       result = 1;
		       char[] strToArr = memory.toCharArray();
		       char nowChar = strToArr[initPos];
		    
    		    for(int i = initPos+1; i < strToArr.length; i++) {
    		        if(nowChar != strToArr[i]) {
    		            result++;
    		            nowChar = strToArr[i];
    		        }
    		    }
		    }

		  
		    System.out.printf("#%d %d%n", test_case, result);
		}
	}
}