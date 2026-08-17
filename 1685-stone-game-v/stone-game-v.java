class Solution {
    int[][] memo;
    int[] pref;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stoneValue[i];
        }
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }
        return solve(0, n - 1, stoneValue);
    }

    private int getSum(int l, int r) {
        return pref[r + 1] - pref[l];
    }

    private int solve(int l, int r, int[] stoneValue) {
        if (l == r) return 0;
        if (memo[l][r] != -1) return memo[l][r];

        int maxScore = 0;
        for (int k = l; k < r; k++) {
            int leftSum = getSum(l, k);
            int rightSum = getSum(k + 1, r);

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(l, k, stoneValue));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, r, stoneValue));
            } else {
                int leftOpt = leftSum + solve(l, k, stoneValue);
                int rightOpt = rightSum + solve(k + 1, r, stoneValue);
                maxScore = Math.max(maxScore, Math.max(leftOpt, rightOpt));
            }
        }
        return memo[l][r] = maxScore;
    }
}
