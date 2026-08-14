class Solution {
    public int maximumLengthSubstring(String s) {
        int ans = 0;
        int[] count = new int[26];
        int l = 0;
        for (int r = 0; r < s.length(); ++r) {
            char rightChar = s.charAt(r);
            count[rightChar - 'a']++;
            while (count[rightChar - 'a'] > 2) {
                count[s.charAt(l) - 'a']--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
