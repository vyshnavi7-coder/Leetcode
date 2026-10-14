import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            List row = (List) triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                int val = ((Number) row.get(j)).intValue();
                dp[j] = val + Math.min(dp[j], dp[j + 1]);
            }
        }
        
        return dp[0];
    }
}
