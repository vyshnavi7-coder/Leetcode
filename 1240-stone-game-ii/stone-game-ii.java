class Solution {
    private int[] sufSum;
    private int[][] memo;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        sufSum = new int[n + 1];
        memo = new int[n][n + 1];
        
        // Calculate suffix sums
        for (int i = n - 1; i >= 0; i--) {
            sufSum[i] = sufSum[i + 1] + piles[i];
        }
        
        return dp(0, 1);
    }

    private int dp(int i, int m) {
        if (i + 2 * m >= n) {
            return sufSum[i];
        }
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        int minOpponent = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * m; x++) {
            minOpponent = Math.min(minOpponent, dp(i + x, Math.max(m, x)));
        }
        
        memo[i][m] = sufSum[i] - minOpponent;
        return memo[i][m];
    }
}
