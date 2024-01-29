import java.util.Scanner;

public class Main

{   
    static int N;
    static String A = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
    static String B = "\"재귀함수가 뭔가요?\"";
    static String C = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
    static String D = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
    static String E = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
    static String F = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
    static String G = "라고 답변하였지.";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
 		N = sc.nextInt();
 		System.out.println(A);
 		recur(0);
		
	}
	    
	static void recur(int cnt) {
	   if (cnt == N) {

	       System.out.println(makeBar(cnt) + B);
	       System.out.println(makeBar(cnt) + F);
	       System.out.println(makeBar(cnt) + G);
	       return;
	   }
	   System.out.println(makeBar(cnt) + B);
	   System.out.println(makeBar(cnt) + C);
	   System.out.println(makeBar(cnt) + D);
	   System.out.println(makeBar(cnt) + E);
	   cnt++;
	   recur(cnt);
	   System.out.println(makeBar(cnt-1) + G);
	}
	
	static String makeBar(int cnt) {
	       String tmpString = "";
	       for(int i = 0; i < cnt; i++) {
	           tmpString += "____";
	       }
	       return tmpString;
	}
}