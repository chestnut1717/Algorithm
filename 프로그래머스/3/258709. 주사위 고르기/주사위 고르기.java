import java.util.*;

class Solution { 
    static int dictCnt;
    static int listCnt;
    static List<int[]> allCombi = new ArrayList<>();
    static int[] combi;
    static List<Integer> sumListA = new ArrayList<>();
    static List<Integer> sumListB = new ArrayList<>();
    static int winMax = Integer.MIN_VALUE;
    public int[] solution(int[][] dice) {
        
        int[] answer = {};
        dictCnt = dice.length;
        listCnt = dictCnt/2;
        combi = new int[dictCnt/2];
        
        // 1. 우선 A가 가져갈 수 있는 조합을 모두 고른다.
        // 그 방법은 일반적인 조합을 활용한다. => 특정 List에 조합 경우(최대 10C5개 저장)
        combination(0, 0);
        
        // 2. 모든 경우가 구해졌으면, 나올 수 있는 모든 경우의 수의 합을 구한다.
        // 우선 A에 대해서 진행
        for(int i = 0; i < allCombi.size()/2; i++) {
            
            int[] arrA = allCombi.get(i);
            
            
            boolean[] visited = new boolean[dictCnt];
            for(int j=0; j<arrA.length; j++){
                visited[arrA[j]] = true;
            }
            // 우선 arrB도 작업 진행
            int[] arrB = new int[dictCnt/2];

            int combBStoreIdx = 0;
            for(int j=0;j<dictCnt; j++){
                if(visited[j] == false){
                    arrB[combBStoreIdx++] = j;
                }
            }

            // arrA와 arrB의 모든 주사위 합의 경우의 수 계산
            sumListA.clear();
            sumListB.clear();
            calc(sumListA, dice, arrA, 0, 0);
            calc(sumListB, dice, arrB, 0, 0);

            // 3. 이진 탐색 하기 위해 오름차순 정렬
            Collections.sort(sumListA);
            Collections.sort(sumListB);

            // 4. 이진 탐색으로 sumList[A]의 원소 아래 sumListB의 현재보다 더 몇개가 있는지 확인하는 코드
            int tmpMax = 0;
            ///////////////////
            for(int pivot: sumListA) {
                tmpMax += binarySearch(sumListB, pivot, 0, sumListB.size()-1);
            }

            if(tmpMax > winMax) {
                winMax = tmpMax;
                answer = Arrays.stream(arrA).toArray();
                System.out.println(arrA[0]);
            }
            
            tmpMax = 0;
            for(int pivot: sumListB) {
                tmpMax += binarySearch(sumListA, pivot, 0, sumListA.size()-1);
            }
            if(tmpMax > winMax) {
                winMax = tmpMax;
                System.out.println(arrA[0]);
                answer = arrB.clone();
            }
        

        }
        
        for(int i=0;i<listCnt;i++){
            answer[i] += 1;
        }
        return answer;
    }
    
    
    static int binarySearch(List<Integer> list, int pivot, int start, int end) {
        
        int left = 0;
        int right = end;
        // left가 right보다 작거나 같을 때
        while(left <= right) {
            int mid = (left + right) / 2;
            if(pivot <= list.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }
    
    
    static void combination(int start, int cnt) {
        // 만약 숫자가 주사위의 갯수/2와 동일하다면
        if(cnt == dictCnt/2) {
            // 해당 숫자를 allCombi에 넣는다.(deepcopy과정)
            allCombi.add(Arrays.stream(combi).toArray());
            return;
        }
        // 그렇지 않으면 조합 탐색
        for(int i = start; i < dictCnt; i++) {
            combi[cnt] = i;
            combination(i+1, cnt+1);
        }
        
    }
    
    static void calc(List<Integer> list, int[][] dice, int[] arr, int cnt, int sum) {
        // 만약 cnt가 depth와 같아진다면 중단(depth는 최대 5)
        if(cnt == listCnt) {
            // 여기서 이제 A가 모든 n/2개의 주사위를 던졌을 때의 합을 저장.
            list.add(sum);
            return;
        }
        for(int i = 0; i < 6; i++) {
            calc(list, dice, arr, cnt+1, sum+dice[arr[cnt]][i]);
        }
    }
}