/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

import java.util.Stack;

/**
 * Problem:
 * Given a stack of n integers in some random order, return the stack in ascending order.
 * 
 * For example, if the stack looks like this:
 * S = 
 * | 10 |
 * | 5  |
 * | 8  |
 * | 3  |
 * | 17 |
 * ------
 * 
 * After the sort, the stack should look like this:
 * S = 
 * | 3  |
 * | 5  |
 * | 8  |
 * | 10 |
 * | 17 |
 * ------
 * 
 * You can only use one other Stack. 
 * The original stack must be returned with the largest value on the bottom and smallest on top.
 * 
 * @author  Michael Markidis
 * 
 */

public class SortStack
{
	public static void main (String[] args)
	{
		Stack<Integer> stackA = new Stack<>();

		stackA.push(17);
		stackA.push(3);
		stackA.push(8);
		stackA.push(5);
		stackA.push(10);

		System.out.println("Before: " + stackA);
		sort(stackA);
		System.out.println(" After: " + stackA);
	}

	/**
	 * Sorts the stack in ascending order.
	 * 
	 * @param stackA - the stack to be sorted
	 */
	private static void sort (Stack<Integer> stackA)
	{
		int numSorted = 0;
		int size = stackA.size();

		// auxiliary stack
		Stack<Integer> stackB = new Stack<>();

		while (numSorted < size)
		{
			// pop first item to be the max
			int max = stackA.pop();

			int numsPops = 0;
			int numUnSorted = size - numSorted;

			// take only the unsorted portion off the stack
			while (!stackA.isEmpty() && numsPops++ < numUnSorted)
			{
				int val = stackA.pop();

				if (val > max)
				{
					stackB.push(max);
					max = val;
				}
				else
				{
					stackB.push(val);
				}
			}

			// push the max on the stack
			stackA.push(max);
			numSorted++;

			// take everything off B and put in A
			while (!stackB.isEmpty())
			{
				stackA.push(stackB.pop());
			}
		}
	}
}
