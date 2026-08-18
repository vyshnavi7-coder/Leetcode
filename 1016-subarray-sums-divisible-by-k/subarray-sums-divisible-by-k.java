class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] remainderCounts = new int[k];
        remainderCounts[0] = 1;
        
        int runningSum = 0;
        int totalSubarrays = 0;
        
        for (int num : nums) {
            runningSum += num;
            int remainder = (runningSum % k + k) % k;
            totalSubarrays += remainderCounts[remainder];
            remainderCounts[remainder]++;
        }
        
        return totalSubarrays;
    }
}
