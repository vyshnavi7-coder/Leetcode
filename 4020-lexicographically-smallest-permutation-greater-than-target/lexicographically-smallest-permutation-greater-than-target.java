class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char ch : s.toCharArray()) count[ch - 'a']++;
        
        int matched = 0;
        while (matched < n && count[target.charAt(matched) - 'a'] > 0) {
            count[target.charAt(matched) - 'a']--;
            matched++;
        }
        
        for (int pos = Math.min(matched, n - 1); pos >= 0; pos--) {
            if (pos < matched) count[target.charAt(pos) - 'a']++;
            
            for (int c = target.charAt(pos) - 'a' + 1; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;
                    StringBuilder sb = new StringBuilder(target.substring(0, pos));
                    sb.append((char) ('a' + c));
                    for (int i = 0; i < 26; i++) {
                        while (count[i]-- > 0) sb.append((char) ('a' + i));
                    }
                    return sb.toString();
                }
            }
        }
        return "";
    }
}
