import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] d = new int[m][n];
        int x = 0, y = 0, cnt = 0;
        
        for (int i = 0; i < m; i++) {
            String row = classroom[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (c == 'S') {
                    x = i;
                    y = j;
                } else if (c == 'L') {
                    d[i][j] = cnt++;
                }
            }
        }
        
        if (cnt == 0) {
            return 0;
        }
        
        boolean[][][][] vis = new boolean[m][n][energy + 1][1 << cnt];
        List<int[]> q = new ArrayList<>();
        q.add(new int[] {x, y, energy, (1 << cnt) - 1});
        vis[x][y][energy][(1 << cnt) - 1] = true;
        
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        
        while (!q.isEmpty()) {
            List<int[]> t = q;
            q = new ArrayList<>();
            for (int[] state : t) {
                int i = state[0], j = state[1], e = state[2], mask = state[3];
                if (mask == 0) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int ni = i + dirs[k], nj = j + dirs[k + 1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && classroom[ni].charAt(nj) != 'X') {
                        int ne = e - 1;
                        if (ne < 0) {
                            continue;
                        }
                        if (classroom[ni].charAt(nj) == 'R') {
                            ne = energy;
                        }
                        int nmask = mask;
                        if (classroom[ni].charAt(nj) == 'L') {
                            nmask &= ~(1 << d[ni][nj]);
                        }
                        if (!vis[ni][nj][ne][nmask]) {
                            vis[ni][nj][ne][nmask] = true;
                            q.add(new int[] {ni, nj, ne, nmask});
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
