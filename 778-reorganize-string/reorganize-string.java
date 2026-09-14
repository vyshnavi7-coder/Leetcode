class Solution {
    public String reorganizeString(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        int maxCount = 0;
        char maxChar = ' ';
        for (int i = 0; i < 26; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                maxChar = (char) (i + 'a');
            }
        }

        if (maxCount > (s.length() + 1) / 2) {
            return "";
        }

        char[] res = new char[s.length()];
        int idx = 0;

        while (counts[maxChar - 'a'] > 0) {
            res[idx] = maxChar;
            idx += 2;
            counts[maxChar - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                counts[i]--;
            }
        }

        return String.valueOf(res);
    }
}
