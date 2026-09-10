import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        int maxSum = nums[0];
        
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && i - deque.peekFirst() > k) {
                deque.pollFirst();
            }
            
            dp[i] = nums[i];
            if (!deque.isEmpty()) {
                dp[i] = Math.max(dp[i], nums[i] + dp[deque.peekFirst()]);
            }
            
            maxSum = Math.max(maxSum, dp[i]);
            
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            
            deque.addLast(i);
        }
        
        return maxSum;
    }
}
