# code-samples
A few samples of my code where I solve various programming questions.

|File | Problem Description | 
|-----|:--------------------|
|[CrawlWebsite.java][CrawlWebsite]|Given a root URL (e.g. http://www.domain.com/) crawl the website n levels deep and collect the number of distinct links (hrefs) at the following levels:<br> 1. The page level - How many distinct links does the page link to <br>2. The level - How many distinct links do all pages at the given depth link to <br> 3. The entire website - How many distinct links does the website link to after crawling n levels deep|
|[SortStack.java][SortStack]|Given a stack of n integers in some random order, return the stack in ascending order. You can only use one other Stack. The original stack must be returned with the largest value on the bottom and smallest on top.|
|[RotateListByK.java][RotateListByK]|You are given a singly linked list, rotate the linked list counter-clockwise by k nodes. Where k is a given positive integer. i.e. if the given linked list is: <br>`1->2->3->4->5` and k is 3, the list should be modified to:<br> `4->5->1->2->3`. <br>Assume that k is smaller than the number of nodes in linked list.|
|[SimplifyUnixPath.java][SimplifyUnixPath]|Given an absolute path for a file (Unix-style), simplify it.<br> For example,<br> `path = "/home/", => "/home"` <br> `path = "/a/./b/../../c/", => "/c"`|
|[AddSumOfLinkedLists.java][AddSumOfLinkedLists]|You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1’s digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.|
|[TotalNumberOfBinaryTrees.java][TotalNumberOfBinaryTrees]|Given n number of nodes, how many structurally unique Binary Trees are there?|

Usage
-----
To compile and run the `linked list` problems, you must include the [Node.java][Node] and [LinkedList.java][LinkedList] files.

License
-------
Copyright 2016 Michael Markidis

Licensed under the [MIT][mitlicense].

[mitlicense]: MIT-LICENSE.txt

[Node]: src/com/samples/Node.java
[LinkedList]: src/com/samples/LinkedList.java
[CrawlWebsite]: src/com/samples/CrawlWebsite.java
[SortStack]: src/com/samples/SortStack.java
[RotateListByK]: src/com/samples/RotateListByK.java
[SimplifyUnixPath]: src/com/samples/SimplifyUnixPath.java
[AddSumOfLinkedLists]: src/com/samples/AddSumOfLinkedLists.java
[TotalNumberOfBinaryTrees]: src/com/samples/TotalNumberOfBinaryTrees.java
