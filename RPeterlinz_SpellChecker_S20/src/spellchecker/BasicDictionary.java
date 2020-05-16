package spellchecker;

import static sbcc.Core.*;

import java.util.*;

public class BasicDictionary implements Dictionary {

	private BinaryTreeNode root;
	private int count;

	@Override
	public void importFile(String filename) throws Exception {
		var words = readFileAsLines(filename);
		this.root = growTree(words);
		this.count = words.size();
	}


	@Override
	public void load(String filename) throws Exception {
		var words = readFileAsLines(filename);
		for (var word : words)
			add(word);
	}


	@Override
	public void save(String filename) throws Exception {
		List<String> file = new ArrayList<String>(Arrays.asList(traversePreorder(root).toString().split(" ")));
		writeFileAsLines(filename, file);
	}


	@Override
	public String[] find(String word) {
		BinaryTreeNode cursor = this.root;
		String[] suggest = { "", "z" };
		while (true) {
			if (cursor == null) {
				if (suggest[1].equals("z"))
					suggest[1] = "";
				return suggest;
			}

			int compare = word.compareToIgnoreCase(cursor.value);
			if (compare < 0) {
				if (cursor.value.compareToIgnoreCase(suggest[1]) < 0)
					suggest[1] = cursor.value;
				cursor = cursor.left;
			} else if (compare > 0) {
				if (cursor.value.compareToIgnoreCase(suggest[0]) > 0)
					suggest[0] = cursor.value;
				cursor = cursor.right;
			} else if (compare == 0)
				return null;
		}
	}


	@Override
	public void add(String word) {
		this.root = sprout(this.root, word);
	}


	@Override
	public BinaryTreeNode getRoot() {
		return this.root;
	}


	@Override
	public int getCount() {
		return this.count;
	}


	/**
	 * Computes the root index of a sorted list to create a complete binary tree,
	 * pretty slick huh?
	 * 
	 * @param words
	 * @return root index
	 */
	private int completeRoot(List<String> words) {
		int n = words.size();
		int a = (int) (n - Math.pow(2, Math.floor(Math.log10(n) / Math.log10(2) - 1)));
		int b = (int) (Math.pow(2, Math.floor(Math.log10(n) / Math.log10(2))) - 1);
		return (a + b - Math.abs(a - b)) / 2;
	}


	/**
	 * Recursive Function for importFile()
	 * 
	 * @param words
	 * @return complete branch (or leaf)
	 */
	private BinaryTreeNode growTree(List<String> words) {
		if (words.size() == 0)
			return null;
		if (words.size() == 1)
			return new BinaryTreeNode(words.get(0).trim());

		int root = completeRoot(words);

		BinaryTreeNode cursor = new BinaryTreeNode(words.get(root).trim());
		cursor.left = growTree(words.subList(0, root));
		cursor.right = growTree(words.subList(root + 1, words.size()));

		return cursor;
	}


	/**
	 * traverses the dictionary's tree in preorder
	 * 
	 * @param cursor
	 * @return StringBuilder of tree in preorder
	 */
	private StringBuilder traversePreorder(BinaryTreeNode cursor) {
		if (cursor == null)
			return new StringBuilder("");

		StringBuilder preorder = new StringBuilder(cursor.value.trim() + " ");
		preorder.append(traversePreorder(cursor.left));
		preorder.append(traversePreorder(cursor.right));

		return preorder;
	}


	/**
	 * recursive function for add()
	 * 
	 * @param cursor
	 * @param word
	 * @return tree with added word
	 */
	private BinaryTreeNode sprout(BinaryTreeNode cursor, String word) {
		if (cursor == null) {
			this.count++;
			return new BinaryTreeNode(word);
		}

		int compare = word.compareToIgnoreCase(cursor.value);
		if (compare < 0)
			cursor.left = sprout(cursor.left, word);
		else if (compare > 0)
			cursor.right = sprout(cursor.right, word);

		return cursor;
	}


	@Override
	public String toString() {
		return traversePreorder(root).toString();
	}
}
