class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][k + 1];
        int[][] sumDp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            sumDp[i][0] = i + 1;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {
                dp[i][j] = (dp[i - 1][j] + sumDp[i - 1][j - 1]) % MOD;
                sumDp[i][j] = (sumDp[i - 1][j] + dp[i][j]) % MOD;
            }
        }

        return dp[n - 1][k];
    }
}
