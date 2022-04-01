package iguigui.leetcodes.linkedList;

public class LC876 {
    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5,6};
        ListNode listNode = ListNodeUtil.buildListNode(ints);
        LC876 lc876 = new LC876();
        System.out.println(lc876.middleNode(listNode).val);
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
