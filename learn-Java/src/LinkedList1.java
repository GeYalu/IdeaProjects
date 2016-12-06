import java.util.Stack;

public class LinkedList1 {
    private Node head;
    private Stack s;

    LinkedList1() {
        head = null;
        s = new Stack();
    }

    private static class Node {
        int item;
        Node next;

        Node() {
            this.item = 0;
            this.next = null;
        }

        Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public void insert(int x) {
        Node node = new Node(x, null);
        Node p = head;
        // 注意链表为空的时候的插入
        if (head == null) {
            head = node;
        }
        // 尾插法
        else {
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
        }
    }

    public void travese(Node head) {
        Node p = head;
        while (p != null) {
            System.out.println(p.item);
            p = p.next;
        }
    }
}

