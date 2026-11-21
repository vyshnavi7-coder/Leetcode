import java.util.*;

class Solution {
    private List<Integer>[] originalTree;
    private List<Integer>[] finalTree;
    private int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        originalTree = new ArrayList[n];
        finalTree = new ArrayList[n];
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            originalTree[i] = new ArrayList<>();
            finalTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            originalTree[parent[i]].add(i);
        }

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        rebuildTree(0, s, lastSeen, parent);
        calculateSizes(0);

        return ans;
    }

    private void rebuildTree(int u, String s, int[] lastSeen, int[] parent) {
        char c = s.charAt(u);
        int prev = lastSeen[c - 'a'];

        if (u != 0) {
            if (prev != -1) {
                finalTree[prev].add(u);
            } else {
                finalTree[parent[u]].add(u);
            }
        }

        lastSeen[c - 'a'] = u;

        for (int v : originalTree[u]) {
            rebuildTree(v, s, lastSeen, parent);
        }

        lastSeen[c - 'a'] = prev;
    }

    private int calculateSizes(int u) {
        int size = 1;
        for (int v : finalTree[u]) {
            size += calculateSizes(v);
        }
        ans[u] = size;
        return size;
    }
}
