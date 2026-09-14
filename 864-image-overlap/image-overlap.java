class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;
        int[][] count = new int[2 * n + 1][2 * n + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    for (int r = 0; r < n; r++) {
                        for (int c = 0; c < n; c++) {
                            if (img2[r][c] == 1) {
                                count[i - r + n][j - c + n]++;
                            }
                        }
                    }
                }
            }
        }
        
        for (int[] row : count) {
            for (int v : row) {
                maxOverlap = Math.max(maxOverlap, v);
            }
        }
        
        return maxOverlap;
    }
}
