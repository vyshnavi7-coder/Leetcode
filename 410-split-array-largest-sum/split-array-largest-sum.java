class Solution {
    public int splitArray(int[] nums, int k) {
        int maxVal = 0;
        int sumVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sumVal += num;
        }
        
        int low = maxVal;
        int high = sumVal;
        int ans = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    private boolean canSplit(int[] nums, int k, int target) {
        int count = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num > target) {
                count++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }
        
        return count <= k;
    }
}
