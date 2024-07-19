import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static String[] schools;
    static List<CustomLinkedList> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		// 학교 이름
		schools = new String[N+1];
		
		for(int i = 0; i <= N; i++) {
		    list.add(new CustomLinkedList(new Node(i)));
		}
		for(int i = 1; i <= N; i++) {
		    schools[i] = br.readLine();
		}
		
		// N-1개 입력 받기
		int last = 0;
		for(int i = 0; i < N-1; i++) {
		    st = new StringTokenizer(br.readLine());
		    int addSchool = Integer.parseInt(st.nextToken());
		    int removeSchool = Integer.parseInt(st.nextToken());
		    
		    list.get(addSchool).addLast(list.get(removeSchool));
		    // 마지막 숫자
		    if (i == N-2) {
		        last = addSchool;
		    }
		}
		
		Node now = list.get(last).head;
		while(now != null) {
		    sb.append(schools[now.num]);
		    now = now.next;
		}

		bw.write(sb.toString());
		bw.close();
	}
	
	static class Node {
	    int num;
	    Node next;
	    public Node(int num) {
	        this.num = num;
	        this.next = null;
	    }
	}
	
	static class CustomLinkedList{
	    Node head;
	    Node tail;
	    
	    public CustomLinkedList(Node node) {
	        this.head = node;
	        this.tail = node;
	    }
	    
	    public void addLast(CustomLinkedList otherList) {
	        this.tail.next = otherList.head;
	        this.tail = otherList.tail;
	    }
	    
	}
}
