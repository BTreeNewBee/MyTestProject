package iguigui.leetcodes.linkedList;


public class LC206 {
    public static void main(String[] args) {
        int[] ints = {1};
        System.out.println(new LC206().reverseList(NodeUtil.buildListNode(ints)));
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;

        while (cur != null) {
            //把下一个存起来
            tmp = cur.next;
            //当前节点的下一个指向前一节点，反转
            cur.next = pre;
            //向后移动，pre后移
            pre = cur;
            //向后移动，cur后移
            cur = tmp;
        }
        return pre;
    }

}
