class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1000000007;

        int[][] destRowR = new int[m][n];
        int[][] destColR = new int[m][n];
        boolean[][] destIsRightR = new boolean[m][n];

        int[][] destRowD = new int[m][n];
        int[][] destColD = new int[m][n];
        boolean[][] destIsRightD = new boolean[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    destRowR[i][j] = i;
                    destColR[i][j] = j;
                    destIsRightR[i][j] = true;
                } else {
                    if (i + 1 < m) {
                        destRowR[i][j] = destRowD[i + 1][j];
                        destColR[i][j] = destColD[i + 1][j];
                        destIsRightR[i][j] = destIsRightD[i + 1][j];
                    } else {
                        destRowR[i][j] = -1;
                    }
                }

                if (grid[i][j] == 0) {
                    destRowD[i][j] = i;
                    destColD[i][j] = j;
                    destIsRightD[i][j] = false;
                } else {
                    if (j + 1 < n) {
                        destRowD[i][j] = destRowR[i][j + 1];
                        destColD[i][j] = destColR[i][j + 1];
                        destIsRightD[i][j] = destIsRightR[i][j + 1];
                    } else {
                        destRowD[i][j] = -1;
                    }
                }
            }
        }

        int[][] dpR = new int[m][n];
        int[][] dpD = new int[m][n];

        if (n > 1) {
            int r = destRowR[0][1];
            int c = destColR[0][1];
            if (r != -1) {
                if (destIsRightR[0][1]) dpR[r][c] = 1;
                else dpD[r][c] = 1;
            }
        }
        if (m > 1) {
            int r = destRowD[1][0];
            int c = destColD[1][0];
            if (r != -1) {
                if (destIsRightD[1][0]) dpR[r][c] = 1;
                else dpD[r][c] = 1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;

                long ways = (dpR[i][j] + dpD[i][j]) % MOD;
                if (ways == 0) continue;

                if (j + 1 < n) {
                    int r = destRowR[i][j + 1];
                    int c = destColR[i][j + 1];
                    if (r != -1) {
                        if (destIsRightR[i][j + 1]) {
                            dpR[r][c] = (int) ((dpR[r][c] + ways) % MOD);
                        } else {
                            dpD[r][c] = (int) ((dpD[r][c] + ways) % MOD);
                        }
                    }
                }

                if (i + 1 < m) {
                    int r = destRowD[i + 1][j];
                    int c = destColD[i + 1][j];
                    if (r != -1) {
                        if (destIsRightD[i + 1][j]) {
                            dpR[r][c] = (int) ((dpR[r][c] + ways) % MOD);
                        } else {
                            dpD[r][c] = (int) ((dpD[r][c] + ways) % MOD);
                        }
                    }
                }
            }
        }

        return (dpR[m - 1][n - 1] + dpD[m - 1][n - 1]) % MOD;
    }
}
