import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long[] counts = new long[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    counts[i]++;
                }
            }
        }
        
        long totalSum = 0;
        long MOD = 1_000_000_007;
        
        for (int j = 0; j < k; j++) {
            long currentNum = 0;
            for (int i = 0; i < 32; i++) {
                if (counts[i] > 0) {
                    currentNum |= (1L << i);
                    counts[i]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}
