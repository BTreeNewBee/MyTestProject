package iguigui.leetcodes.linkedList;

import java.util.ArrayList;
import java.util.List;

public class NodeUtil {

    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1,null,3,2,4,null,5,6};
        Node node = buildNode(ints);
        System.out.println(node);
    }

    //生成链表
    public static ListNode buildListNode(int[] value) {
        ListNode head = new ListNode(value[0]);
        ListNode headTmp = head;
        for (int i = 1; i < value.length; i++) {
            head.next = new ListNode(value[i]);
            head = head.next;
        }
        return headTmp;
    }

    //生成多叉树
    public static Node buildNode(Integer[] value) {
        Node head = new Node(value[0]);
        Node next = head;
        List<Node> nodes = new ArrayList<>();
        nodes.add(head);
        int n = 0;
        List<Node> childs = new ArrayList<>();
        for (int i = 2; i < value.length; i++) {
            if (value[i] == null) {
                next.children = childs;
                n ++;
                next = nodes.get(n);
                childs = new ArrayList<>();
                continue;
            }
            Node node = new Node(value[i]);
            childs.add(node);
            nodes.add(node);
        }
        next.children = childs;
        return head;
    }

}
