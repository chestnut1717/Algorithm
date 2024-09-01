import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int start = 0;
        int end = people.length-1;
        int answer = 0;
        
        
        Arrays.sort(people); // O(NlogN)
        
        /// two pointer로 풀어야 함, O(N)
        while(start <= end) {
            if(people[start] + people[end] <= limit) {
                start++;
                end--;
            } else {
                end--;
            }
            answer++;
            if(start==end) {
                answer++;
                break;
            }
        }
        
        
        return answer;

    }
}  