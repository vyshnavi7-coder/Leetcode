class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLeft = new int[n];
        java.util.Arrays.fill(minLeft, Integer.MAX_VALUE);
        
        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int currentLen = right - left + 1;
                if (left > 0 && minLeft[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currentLen + minLeft[left - 1]);
                }
                minLen = Math.min(minLen, currentLen);
            }
            
            minLeft[right] = minLen;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
