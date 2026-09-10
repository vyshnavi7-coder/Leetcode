import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSums = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        
        int minLength = n + 1;
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i <= n; i++) {
            while (!deque.isEmpty() && prefixSums[i] - prefixSums[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            
            while (!deque.isEmpty() && prefixSums[i] <= prefixSums[deque.peekLast()]) {
                deque.pollLast();
            }
            
            deque.addLast(i);
        }
        
        return minLength <= n ? minLength : -1;
    }
}
