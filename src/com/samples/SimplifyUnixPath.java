/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

import java.util.Stack;

/**
 * Problem:
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * @author  Michael Markidis
 * 
 */

public class SimplifyUnixPath
{
	public static void main (String[] args)
	{
		String path = "/a/./b/../../c/";

		String sPath = simplifyPath(path);
		System.out.println(sPath);
	}

	/**
	 * Returns the simplified path of the passed in absolute path for a file (Unix-style).
	 * 
	 * @param absPath - the absolute path for a file
	 * @return the simplified path
	 */
	private static String simplifyPath (String absPath)
	{
		if (absPath == null)
			absPath = ""; 

		String [] tokens = absPath.split("/");
		Stack<String> stack = new Stack<>();

		// go through each token
		for (String tok : tokens)
		{
			tok = tok.trim();

			// ignore the current directory reference
			if(tok.length() > 0 && !tok.equals(".") )
			{
				// need to go back up a directory
				if (tok.equals(".."))
				{
					// check if something is on the stack before 
					if (!stack.isEmpty())
						stack.pop();
				}
				else // we have an actual dir/file name
				{
					stack.push(tok);
				}
			}
		}

		String result = "";

		if (stack.isEmpty())
		{
			result = "/";
		}

		// pop the values off the stack to create the path
		while (!stack.isEmpty())
		{
			String dir = stack.pop();
			result = "/" + dir + result;
		}

		return result;
	}
}
