package LordShiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoubleNumberRepresentedLinkedList {
  /**
   * https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/
   * 
   * As simple as it can get.
   * 
   * TC: O(n) SC: O(1)
   */

  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode doubleIt(ListNode head) {
    List<Integer> arr = new ArrayList<>();
    ListNode aaa = head;
    while(aaa!=null){
      arr.add(aaa.val);
      aaa = aaa.next;
    }
    Collections.reverse(arr);
    int carry = 0;
    for(int i=0; i<arr.size(); i++){
      int sum = carry+arr.get(i)*2;
      arr.set(i, sum%10);
      carry = sum/10;
    }
    if(carry != 0) arr.add(carry);
    Collections.reverse(arr);
    ListNode res = null;
    if(arr.size()>0) {
      res = new ListNode(arr.get(0));
      aaa = res;
    }
    for(int i=1; i<arr.size(); i++){
      aaa.next = new ListNode(arr.get(i));
      aaa = aaa.next;
    }
    return res;
  }
}
