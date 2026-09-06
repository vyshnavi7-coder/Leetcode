class Solution {
    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        
        if (sLen < tLen) return 0;
        
        int[] dp = new int[tLen + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= sLen; i++) {
            for (int j = tLen; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[tLen];
    }
}
