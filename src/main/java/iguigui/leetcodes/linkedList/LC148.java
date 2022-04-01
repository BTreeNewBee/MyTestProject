package iguigui.leetcodes.linkedList;

public class LC148 {
    public static void main(String[] args) {

    }

    public ListNode sortList(ListNode head) {

        return null;
    }

    //尝试找到链表的中间节点
    //使用快慢指针实现，
    public ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
