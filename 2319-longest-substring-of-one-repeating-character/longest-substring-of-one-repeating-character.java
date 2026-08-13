import java.util.Arrays;

class Solution {
    private static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char prefChar;
        char suffChar;
        int size;

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.prefChar = c;
            this.suffChar = c;
            this.size = 1;
        }

        Node() {
            this.maxLen = 0;
            this.prefLen = 0;
            this.suffLen = 0;
            this.prefChar = '#';
            this.suffChar = '#';
            this.size = 0;
        }
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];
        
        build(1, 0, n - 1);
        
        int k = queryIndices.length;
        int[] ans = new int[k];
        
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            chars[idx] = c;
            update(1, 0, n - 1, idx, c);
            ans[i] = tree[1].maxLen;
        }
        
        return ans;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.size = left.size + right.size;
        res.prefChar = left.prefChar;
        res.suffChar = right.suffChar;
        
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        res.prefLen = left.prefLen;
        res.suffLen = right.suffLen;
        
        if (left.suffChar == right.prefChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
            if (left.prefLen == left.size) {
                res.prefLen = left.size + right.prefLen;
            }
            if (right.suffLen == right.size) {
                res.suffLen = right.size + left.suffLen;
            }
        }
        
        return res;
    }
}
