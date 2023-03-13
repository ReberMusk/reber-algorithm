package com.reber.algorithm.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Tong yuan
 * @version 1.0, 2023-03-11 16:08:00
 */
public class SortedListToBST {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // 先解析为 array 存储
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        // 找到中位数然后构建2叉数
        return buildTree(0, list.size() - 1, list);
    }

    TreeNode buildTree(int left, int right, List<Integer> list){
        if(left > right)return null;
        int mid = left + (right - left + 1) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildTree(left, mid - 1, list);
        root.right = buildTree(mid + 1, right, list);
        return root;
    }

    /**
     * 快慢指针，找到中心节点后，构建二叉树
     *
     * @param head 头
     * @return {@link TreeNode}
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // 快慢指针找中心节点
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;

        // 使用快慢排序快速找到中心节点，之后拆分后构建左右子树。
        TreeNode root = new TreeNode(p.val);
        root.left = sortedListToBST2(head);
        root.right = sortedListToBST2(p.next);
        return root;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {

            this.val = val;

            this.left = left;

            this.right = right;

        }
    }

}
