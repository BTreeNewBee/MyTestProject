package iguigui.leetcodes.linkedList;

public class ListNodeUtil {

    public static ListNode buildListNode(int[] value) {
        ListNode head = new ListNode(value[0]);
        ListNode headTmp = head;
        for (int i = 1; i < value.length; i++) {
            head.next = new ListNode(value[i]);
            head = head.next;
        }
        return headTmp;
    }

}
