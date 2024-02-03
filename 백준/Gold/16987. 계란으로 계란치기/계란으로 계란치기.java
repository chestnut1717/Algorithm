import java.util.*;
import java.io.*;

public class Main {
    static int N; // 계란의 수
    static int[] eggSolid; // 계란의 내구도 배열
    static int[] eggGram; // 계란의 무게 배열
    static int result = -1; // 정답
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    StringTokenizer st;
        	   
	    // 이제 N개 계란 정보 담음
	    eggSolid = new int[N];
	    eggGram = new int[N];
	    for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggSolid[i] = Integer.parseInt(st.nextToken());
            eggGram[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    // 계란 침
        hitEgg(0, 0);
	    
		System.out.println(result);
	}
	
	// 계란 hit
	static void hitEgg(int idx, int crackEgg) {
	    result = Math.max(result, crackEgg); // 재귀 들어올 때 마다 갱신함 => 어차피 최댓값은 무조건 반영되므로
	    if (idx == N) { // 이제 더 이상 칠수 없는경우(마지막 오른쪽계란)
	        return;
	    }
        // 깨졌으면
        if(isCracked(idx)) {
            hitEgg(idx+1, crackEgg); // 오른쪽으로 이동
        } else {
            // 내구도 떨어트리기
            for(int nextIdx = 0; nextIdx < N; nextIdx++) {
                if(idx == nextIdx || eggSolid[nextIdx] <= 0) { continue; } // 자기 자신이거나, 비교 대상 내구도가 0 이하일 경우 스킵
                // 내구도 저장
	            
                eggSolid[idx] -= eggGram[nextIdx];
                eggSolid[nextIdx] -= eggGram[idx];
                
                
                int newCrackEgg = crackEgg;
                if(eggSolid[idx] <= 0) newCrackEgg++;
                if(eggSolid[nextIdx] <= 0) newCrackEgg++;
                
                hitEgg(idx+1, newCrackEgg);// 오른쪽으로 이동
                

                // 다음 경우의 수 계산하기 위해 친 계란을 다시 원상복구해줘야 한다.
                eggSolid[idx] += eggGram[nextIdx];
	            eggSolid[nextIdx] += eggGram[idx];
            }
        }
	}
	
	// 내구도 테스트(0보다 작으면 깨짐)
	static boolean isCracked(int idx) {
	    return eggSolid[idx] <= 0;
	}
	
}
