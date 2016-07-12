/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

/**
 * Problem:
 * Given n number of nodes, how many structurally unique Binary Trees are there? 
 * 
 * @author Michael Markidis
 * 
 */

public class TotalNumberOfBinaryTrees
{
	public static void main (String[] args)
	{
		int n = 10;
		int num = numOfTrees(n);

		System.out.println(num);
	}

	/**
	 * Recursively computes the number of structurally unique Binary Trees that exists 
	 * given n number of nodes.
	 * 
	 * @param n - the number of nodes.
	 * @return - the number of structurally unique Binary Trees for n number of nodes.
	 */
	private static int numOfTrees (int n)
	{
		// base cases: a tree of 0 or 1 nodes can only be structured one way.
		if (n < 2)
		{
			return 1;
		}

		// 2 or more nodes
		int num = 0;
		int nextLevel = n - 1;

		// loop from 0 to n - 1 (nextLevel) and take the sum of the product of all combinations.
		// For example, if n == 3, 
		// then the sum = 
		// 	[numOfTrees(0) * numOfTrees(2)] + 
		// 	[numOfTrees(1) * numOfTrees(1)] + 
		// 	[numOfTrees(2) * numOfTrees(0)] 
		for (int i = 0; i <= nextLevel; i++)
		{
			int leftNum = numOfTrees(i);
			int rightNum = numOfTrees(nextLevel - i);

			num += (leftNum * rightNum);
		}
		return num;
	}
}
