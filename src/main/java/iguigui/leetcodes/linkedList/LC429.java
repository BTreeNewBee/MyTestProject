package iguigui.leetcodes.linkedList;

import iguigui.leetcodes.arrays.LCT;

import java.util.ArrayList;
import java.util.List;

public class LC429 {

    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14};
        Node node = NodeUtil.buildNode(ints);
        System.out.println(new LC429().levelOrder(node));

    }
    
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        levelOrder(root,0,list);
        return list;
    }

    public void levelOrder(Node root,int level,List<List<Integer>> result) {
        if (root == null) {return ;}
        List<Integer> integers;
        if (result.size() - 1 < level) {
            integers = new ArrayList<>();
            result.add(integers);
        } else {
            integers = result.get(level) ;
        }
        integers.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                levelOrder(child,level + 1,result);
            }
        }
    }

}
