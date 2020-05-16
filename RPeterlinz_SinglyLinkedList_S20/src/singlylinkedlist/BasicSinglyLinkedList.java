package singlylinkedlist;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

public class BasicSinglyLinkedList implements SinglyLinkedList {

	private Node top = null;
	private Node last = null;

	private int count = 0;

	@Override
	public Node getFirst() {
		// TODO Auto-generated method stub
		return top;
	}


	@Override
	public Node getLast() {
		// TODO Auto-generated method stub
		return last;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return count == 0;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		top = null;
		last = null;
		count = 0;
	}


	@Override
	public Node insert(Node cursor, String value) {
		var newNode = new Node(value);

		if (cursor != null) { // Not inserting at the top
			newNode.next = cursor.next;
			cursor.next = newNode;
		} else { // inserting at the top
			newNode.next = top;
			top = newNode;
		}

		if (newNode.next == null)
			last = newNode;

		count++;
		return newNode;
	}


	@Override
	public Node append(String value) {
		return insert(last, value);
	}


	@Override
	public void remove(Node cursor) {
		if (cursor == null)
			return;
		if (cursor == top) { // delete top node
			top = cursor.next;
			if (cursor.next == null) // only node
				last = null;
		} else { // not deleting top node
			var prev = top;
			while (prev.next != cursor)
				prev = prev.next;
			if (cursor.next == null)// deleting last node in list
				last = prev;
			prev.next = cursor.next;
		}
		cursor.next = null;

		count--;
	}


	@Override
	public Node find(Node start, String key) {
		var cursor = start;

		while (cursor != null) {
			if (cursor.value.equals(key))
				return cursor;
			cursor = cursor.next;
		}
		return null;
	}


	@Override
	public String toCsvString() {
		var sb = new StringBuilder();
		var cursor = top;

		while (cursor != null) {
			if (sb.length() != 0)
				sb.append(",");
			sb.append(cursor);
			cursor = cursor.next;
		}

		return sb.toString();
	}


	@Override
	public ArrayList<String> toList() {
		ArrayList<String> list = new ArrayList<String>();
		var cursor = top;

		while (cursor != null) {
			list.add(cursor.value);
			cursor = cursor.next;
		}

		return list;
	}


	@Override
	public void loadFile(String filename) throws IOException {
		// TODO Auto-generated method stub
		var list = readFileAsLines(filename);

		for (var entry : list)
			this.append(entry);
	}


	@Override
	public void saveFile(String filename) throws IOException {
		// TODO Auto-generated method stub
		writeFileAsLines(filename, this.toList());
	}

}
