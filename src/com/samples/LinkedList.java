/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

/**
 * Just a simple class that represents a singly-linked list.
 * 
 * @author  Michael Markidis
 * 
 */

public class LinkedList<T>
{
	// reference to the head
	private Node<T> head = null;

	/**
	 * Creates an empty linked list.
	 */
	public LinkedList()
	{}

	/**
	 * Gets the head of this linked list.
	 * 
	 * @return the head node.
	 */
	public Node<T> getHead ()
	{
		return head;
	}

	/**
	 * Sets the head of this linked list.
	 * 
	 * @param newHead - the node that will be the new head.
	 */
	public void setHead(Node<T> newHead)
	{
		head = newHead;
	}

	/**
	 * Adds a new node to the end of this linked list.
	 * 
	 * @param data - the data to be set in the new node.
	 */
	public void add (T data)
	{
		// create the new node
		Node<T> newNode = new Node<T>(data);

		// list is empty
		if (head == null)
		{
			head = newNode;
		}
		else
		{
			Node<T> curr = head;

			// iterate to the end of the list
			while (curr.getNext() != null)
			{
				curr = curr.getNext();
			}

			// the new end will become the new node
			curr.setNext(newNode);
		}
	}

	/**
	 * Returns a string representation of this list.  
	 * 
	 * The string is of the form:
	 * <pre>
	 * head-->node data-->node data-->null
	 * </pre>
	 * 
	 * @return a string representation of this list
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("head-->");

		Node<T> curr = head;

		// the empty list case
		if (curr == null)
		{
			sb.append("null");
			return sb.toString();
		}

		// append the rest of the nodes
		sb.append(curr.getData());
		curr = curr.getNext();
		while (curr != null)
		{
			sb.append("-->");
			sb.append(curr.getData());
			curr = curr.getNext();
		}

		// null tail at the end
		sb.append("-->null");

		return sb.toString();
	}
}
