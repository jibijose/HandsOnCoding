Question 2:  Rotate a Linked List

Given a singly linked list, rotate the linked list counter-clockwise by k nodes. Where k is a given positive integer smaller than or equal to length of the linked list. For example, if the given linked list is 10->20->30->40->50->60 and k is 4, the list should be modified to 50->60->10->20->30->40.
Input:
In this problem, complete the method which takes two argument: the head of the linked list and int k. 
We should not read any input from stdin/console.
The struct Node has a data part which stores the data and a next pointer which points to the next element of the linked list. 
There are multiple test cases. For each test case, this method will be called individually.

Output:
Rotate the link list from index k and return its new head pointer.

Example:

8 (total linked list)
1 2 3 4 5 6 7 8  (Array)
4  (Rotate from the 4th pointer)

Output:
5 6 7 8 1 2 3 4
