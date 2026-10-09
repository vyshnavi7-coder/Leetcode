class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        int s1 = (sum + target) / 2;
        int[] dp = new int[s1 + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = s1; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[s1];
    }
}
