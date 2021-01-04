package notme.leetcode;

/**
 *
 * <div class="notranslate"><p>给你一个链表和一个特定值<em> </em><code>x</code> ，请你对链表进行分隔，使得所有小于 <code>x</code> 的节点都出现在大于或等于 <code>x</code> 的节点之前。</p>
 *
 * <p>你应当保留两个分区中每个节点的初始相对位置。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = 1-&gt;4-&gt;3-&gt;2-&gt;5-&gt;2, <em>x</em> = 3
 * <strong>输出：</strong>1-&gt;2-&gt;2-&gt;4-&gt;3-&gt;5
 * </pre>
 * </div>
 * 链接：<a href ="https://leetcode-cn.com/problems/partition-list">https://leetcode-cn.com/problems/partition-list</a>
 */
public class  _86_Partition_List {
    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        a.next = new ListNode(1);
        ListNode result = new _86_Partition_List().partition(a, 2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }


    public ListNode partition(ListNode head, int x) {
        ListNode H = new ListNode();
        H.next = head;
        ListNode pre = H;
        ListNode BiggerNodes = new ListNode();
        ListNode p = BiggerNodes;
        ListNode q = head;
        while (q != null) {
            if (q.val >= x) {
                p.next = q;
                pre.next = q.next;
                q = q.next;
                p = p.next;
                p.next = null;
            }
            else {
                pre = q;
                q = q.next;
            }
        }
        pre.next = BiggerNodes.next;
        return H.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode() {
    }
}
