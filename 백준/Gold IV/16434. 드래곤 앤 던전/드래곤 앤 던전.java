import java.util.*;
import java.io.*;

public class Main {
    static int N, atk;
    static long hp;
    static long tmpHp;
    static long tmpAtk;
    static long result;
    static List<List<Integer>> lst = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		atk = Integer.parseInt(st.nextToken());
		
		long start = 1L;
		long end = 0;
		
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    int cmd = Integer.parseInt(st.nextToken());
		    int atk = Integer.parseInt(st.nextToken());
		    int hp = Integer.parseInt(st.nextToken());
		    lst.add(Arrays.asList(cmd, atk, hp));
		    if(cmd == 1) end += ((long)atk * hp);
		}
		while(start <= end) {
		    
		    long mid = (start + end) / 2;
		    tmpHp = mid; // 현재 체력
		    tmpAtk = atk; // 현재 공격력
		    boolean success = game(mid);
		    // 만약 공격이 성공했으면
		    if (success) {
		        result = mid;
		        end = mid-1; // 최소 체력을 얻기 위해 내려감
		    }
		    // 실패하면
		    else {
		        start = mid+1; // 경우의 수를 찾기 위해 증가
		    }
		    
		}
		System.out.println(result);
	}
	
	// 명령어들을 하나씩 꺼내서 던전 돌림
	static boolean game(long mid) {
        // 저장되어 있는 명령어들을 다시 꺼내서 던전 돌린다
        for(List<Integer> tmpLst: lst) {
            int cmd = tmpLst.get(0);
            int atk = tmpLst.get(1);
            int hp = tmpLst.get(2);
            // 공격하기
            if(cmd == 1) { 
		        boolean flag = attack(atk, hp);
		        if (flag == false) return false; // 공격 실패 => 종료
		    }
		    // 회복하기
		    else if (cmd == 2) {
		        mana(atk, hp, mid);
		    }
        }
		return true;
	}
	// 마나를 먹으면 공격력과 체력 회복
	static void mana(int atkUp, int hpUp, long mid) {
	    tmpHp += hpUp;
	    tmpAtk += atkUp;
	    if(tmpHp > mid) tmpHp = mid;
	}
	static boolean attack(int monAtk, int monHp) {
	    if (monHp % tmpAtk == 0) {
	        tmpHp -= monAtk * ((monHp / tmpAtk) - 1);
	    } else {
	        tmpHp -= monAtk * ((monHp / tmpAtk));
	    }
	    
	   // System.out.printf("cnt: %d. tmpHp: %d monHp: %d %n", cnt, tmpHp, monHp);
	    if(tmpHp <= 0) {
	        return false;
	    }
	    return true;
	}
}