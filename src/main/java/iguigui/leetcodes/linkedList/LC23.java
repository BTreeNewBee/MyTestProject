package iguigui.leetcodes.linkedList;


import java.util.*;

public class LC23 {
    public static void main(String[] args) {
        ListNode listNode = NodeUtil.buildListNode(new int[]{1, 4, 5});
        ListNode listNode1 = NodeUtil.buildListNode(new int[]{1, 3, 4});
        ListNode listNode2 = NodeUtil.buildListNode(new int[]{2,6});
        ListNode[] lists = new ListNode[]{listNode,listNode1,listNode2};
        System.out.println(new LC23().mergeKLists(lists));
    }


    static class ListNodeWithIndex{
        ListNode listNode;
        int index;
        public ListNodeWithIndex(ListNode listNode, int index) {
            this.listNode = listNode;
            this.index = index;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        //生成头节点，遍历一遍头节点复杂度 k
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            if (lists[i].val < min) {
                min = lists[i].val;
                minIndex = i;
            }
        }
        if (minIndex == -1) {
            return null;
        }
        ListNode head = lists[minIndex];
        ListNode tmpHead = head;
        lists[minIndex] = lists[minIndex].next;
        //最小堆初始化，复杂度k
        PriorityQueue<ListNodeWithIndex> nodes = new PriorityQueue<>(Comparator.comparingInt(e -> e.listNode.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                nodes.add(new ListNodeWithIndex(lists[i],i));
                lists[i] = lists[i].next;
            }
        }
        //开始对最小堆进行弹出操作
        while (nodes.peek() != null) {
            ListNodeWithIndex poll = nodes.poll();
            head.next = poll.listNode;
            head = head.next;
            if (lists[poll.index] != null) {
                nodes.add(new ListNodeWithIndex(lists[poll.index],poll.index));
                lists[poll.index] = lists[poll.index].next;
            }
        }
        return tmpHead;
    }



    //暴力遍历排序法，复杂度nlogn，就也不是不能用但是不极致
    public ListNode mergeKLists1(ListNode[] lists) {
        //复杂度n
        ArrayList<Integer> integers = new ArrayList<>();
        for (ListNode list : lists) {
            while (list != null) {
                integers.add(list.val);
                list = list.next;
            }
        }
        //复杂度 nlogn
        Collections.sort(integers);
        if (integers.size() == 0) {
            return null;
        }
        //复杂度n
        ListNode head = new ListNode(integers.get(0));
        ListNode head1 = head;
        for (int i = 1; i < integers.size(); i++) {
            head.next = new ListNode(integers.get(i));
            head = head.next;
        }
        return head1;
    }

}
