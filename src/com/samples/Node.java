/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

/**
 * Just a simple class that represents a node in a singly-linked list.
 * 
 * @author  Michael Markidis
 */

public class Node<T>
{
	private Node<T> next = null; 
	private T data = null;

	/**
	 * Creates a node.
	 * 
	 * @param data - the data to place in this node.
	 */
	public Node (T data)
	{
		this.data = data;
	}

	/**
	 * Gets the data in this node.
	 * 
	 * @return - the data in this node.
	 */
	public T getData()
	{
		return data;
	}

	/**
	 * Gets the next node reference for this node.
	 * 
	 * @return - the next node reference or null if there is no next.
	 */
	public Node<T> getNext ()
	{
		return next;
	}

	/**
	 * Sets the next node reference for this node.
	 * 
	 * @param next - the node to set as next.
	 */
	public void setNext (Node<T> next)
	{
		this.next = next;
	}
}
