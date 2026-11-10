import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        final int inf = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, inf);
        dp[0] = 0;
        
        for (int i = 1; i <= n; ++i) {
            int currentStepCost = costs[i - 1];
            for (int j = Math.max(0, i - 3); j < i; ++j) {
                dp[i] = Math.min(dp[i], dp[j] + currentStepCost + (i - j) * (i - j));
            }
        }
        
        return dp[n];
    }
}
