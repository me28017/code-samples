/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

/**
 * Problem:
 * You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1’s digit is at the head of
 * the list. Write a function that adds the two numbers and returns the sum as a linked list.
 * 
 * EXAMPLE
 * 	Input: (6 -> 5 -> 2) + (8 -> 6 -> 7)
 * 	Output: 4 -> 2 -> 0 -> 1
 * 
 * Because 256 + 768 = 1024
 * 
 * @author  Michael Markidis
 */

public class AddSumOfLinkedLists
{
	public static void main (String[] args)
	{
		LinkedList<Integer> list1 = new LinkedList<>();
		list1.add(6);
		list1.add(5);
		list1.add(2);

		LinkedList<Integer> list2 = new LinkedList<>();
		list2.add(8);
		list2.add(6);
		list2.add(7);

		long l1Num = convertListToInt(list1);
		long l2Num = convertListToInt(list2);
		long sum = l1Num + l2Num;

		LinkedList<Integer> listSum = new LinkedList<>();

		// create the sum list from the sum of the two lists
		do
		{
			listSum.add((int)sum % 10);
			sum = sum / 10;
		}
		while (sum > 0);

		System.out.println("list1: " + list1);
		System.out.println("list2: " + list2);
		System.out.println("  sum: " + listSum);
	}

	/**
	 * Sums up the individual nodes of the passed in linked list.
	 * 
	 * Note: The digits are stored in reverse order, such that the 1’s digit 
	 * is at the head of the list. 
	 * 
	 * @param list - the linked list to sum.
	 * @return a long representing the sum.
	 */
	private static long convertListToInt (LinkedList<Integer> list)
	{
		long sum = 0;

		Node<Integer> curr = list.getHead();

		// empty list
		if (curr == null)
		{
			return 0;
		}

		long multiplier = 1;

		// iterate through all nodes and keep a running sum
		while (curr != null)
		{
			int i = curr.getData();
			sum = sum + (i * multiplier);

			// increase the significance of the digits 
			multiplier *= 10;

			curr = curr.getNext();
		}
		return sum;
	}
}
