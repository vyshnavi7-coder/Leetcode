class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int MOD = 1000000007;
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;

        for (int k = 0; k < group.length; k++) {
            int members = group[k];
            int p = profit[k];
            for (int i = n; i >= members; i--) {
                for (int j = minProfit; j >= 0; j--) {
                    int nextProfit = Math.min(minProfit, j + p);
                    dp[i][nextProfit] = (dp[i][nextProfit] + dp[i - members][j]) % MOD;
                }
            }
        }

        int totalSchemes = 0;
        for (int i = 0; i <= n; i++) {
            totalSchemes = (totalSchemes + dp[i][minProfit]) % MOD;
        }

        return totalSchemes;
    }
}
