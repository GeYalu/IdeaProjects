
public class LinkNode_Test {
    /**
     * licong 2016/11/16 java实现链表的操作 思路1.建立节点，每个节点指向，一个节点
     * 2.除了链表的头，其他的操作，都会跟3个节点有联系
     * 3.特殊的还有空链表的时候
     * 4.注意相对变量的书写
     */






    public static void main(String[] args) {

        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = new ListNode(0, node1);

        ListNode temp ;

        head.travese(head);
        System.out.println("-----------");
        temp = head.swapNodes(head, 1, 2);
        System.out.println("-----------");
        head.travese(temp);







    }
}

class ListNode {
    private int val;
    private ListNode next;

    public ListNode(int val, ListNode next) {
        super();
        this.val = val;
        this.next = next;

    }


    public void travese(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }

    public static ListNode removeNode(ListNode head, int val) {

        ListNode front = head;

        if (head == null) {
            return null;
        }//空链表特殊，所以要单独写
        if (head.val == 3) {
            return front.next;
        }//头部特殊，所以要单独写
        while (front.next != null) {//写通用部分的。判断是否到了链表的结尾
            if (front.next.val == val) {
                front.next = front.next.next;
            }//因为链表的都是地址，所以，不用计较别的，
            else {
                front = front.next;
            }//注意这些变量，必须写的是相对的变量
        }
        return head;
    }

    public ListNode swapNodes(ListNode head, int v1, int v2) {

        if (head == null) {
            return head;
        }

        ListNode h = head;
        ListNode n = head.next;


        if (h.val == v1 && n.val == v2) {
            ListNode temp3 = n.next;

            n.next=h;
            h.next=temp3;


            System.out.println("h "+h.val);
            System.out.println("n "+n.val);
            System.out.println("temp3 "+temp3.val);
            return n;
        }


        ListNode tmp1 = null;//new ListNode(0,null);
        ListNode tmp2 = null;//new ListNode(0,null);
        ListNode tmp1Prev = new ListNode(0, null);
        ListNode tmp2Prev = new ListNode(0, null);
        ListNode temp2 = new ListNode(0, null);

        while (h.next != null) {
            if (n.val == v1) {
                tmp1Prev = h;
                tmp1 = n;
            }
            if (n.val == v2) {
                tmp2Prev = h;
                tmp2 = n;
            }
            h = h.next;
            n = h.next;
        }

        if (tmp2 == null || tmp1 == null) {
            return head;
        }

        System.out.println("--------");
        System.out.println(tmp1Prev.val);
        System.out.println(tmp1.val);
        System.out.println(tmp2Prev.val);
        System.out.println(tmp2.val);
        System.out.println("--------");

        temp2.next = tmp2.next;

        travese(head);
        ListNode temp = head;

        tmp1Prev.next = tmp2;
        tmp2.next = tmp1.next;

        tmp2Prev.next = tmp1;
        tmp1.next = temp2.next;

        System.out.println("$$$$$$$$$");

        travese(head);
        return null;
    }
}
