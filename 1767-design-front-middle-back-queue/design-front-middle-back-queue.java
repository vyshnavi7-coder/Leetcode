class FrontMiddleBackQueue {
    private java.util.Deque<Integer> left;
    private java.util.Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new java.util.LinkedList<>();
        right = new java.util.LinkedList<>();
    }
    
    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }
    
    public void pushMiddle(int val) {
        if (left.size() > right.size()) {
            right.addFirst(left.removeLast());
        }
        left.addLast(val);
    }
    
    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }
    
    public int popFront() {
        if (left.isEmpty() && right.isEmpty()) return -1;
        int val = left.isEmpty() ? right.removeFirst() : left.removeFirst();
        balance();
        return val;
    }
    
    public int popMiddle() {
        if (left.isEmpty() && right.isEmpty()) return -1;
        int val;
        if (left.size() == right.size()) {
            val = left.removeLast();
        } else {
            val = left.size() > right.size() ? left.removeLast() : right.removeFirst();
        }
        balance();
        return val;
    }
    
    public int popBack() {
        if (left.isEmpty() && right.isEmpty()) return -1;
        int val = right.isEmpty() ? left.removeLast() : right.removeLast();
        balance();
        return val;
    }

    private void balance() {
        if (left.size() > right.size() + 1) {
            right.addFirst(left.removeLast());
        } else if (right.size() > left.size()) {
            left.addLast(right.removeFirst());
        }
    }
}
