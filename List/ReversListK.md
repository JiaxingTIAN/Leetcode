#Reverse LinkedList in K groups

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
```
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
```
* Recursion:
```java
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}
```
* Iterative
```java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode begin;
    if (head==null || head.next ==null || k==1)
    	return head;
    ListNode dummyhead = new ListNode(-1);
    dummyhead.next = head;
    begin = dummyhead;
    int i=0;
    while (head != null){
    	i++;
    	if (i%k == 0){
    		begin = reverse(begin, head.next);
    		head = begin.next;
    	} else {
    		head = head.next;
    	}
    }
    return dummyhead.next;
    
}

public ListNode reverse(ListNode begin, ListNode end){
	ListNode curr = begin.next;
	ListNode next, first;
	ListNode prev = begin;
	first = curr;
	while (curr!=end){
		next = curr.next;
		curr.next = prev;
		prev = curr;
		curr = next;
	}
	begin.next = prev;
	first.next = curr;
	return first;
}
```