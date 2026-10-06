class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            Node copy = new Node(curr.val);
            curr.next = copy;
            copy.next = next;
            curr = next;
        }

        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;
        Node dummy = new Node(0);
        Node copyCurr = dummy;

        while (curr != null) {
            Node next = curr.next.next;
            Node copy = curr.next;

            copyCurr.next = copy;
            copyCurr = copy;

            curr.next = next;
            curr = next;
        }

        return dummy.next;
    }
}
