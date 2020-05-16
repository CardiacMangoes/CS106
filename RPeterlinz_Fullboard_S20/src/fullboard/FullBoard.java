package fullboard;

import static sbcc.Core.*;

import java.util.*;

public class FullBoard {

	private int moves;

	private Board initialBoard;
	private LinkedList<Board> queue = new LinkedList<Board>();
	private ArrayList<Board> solvedBoards = new ArrayList<Board>();
	private HashMap<Character, int[]> way = new HashMap<Character, int[]>();

	public FullBoard() {
		way.put('→', new int[] { 0, 1 });
		way.put('←', new int[] { 0, -1 });
		way.put('↑', new int[] { -1, 0 });
		way.put('↓', new int[] { 1, 0 });
	}


	/**
	 * meat of the code, procedure to find best path through brute force
	 * 
	 * @param boardArray
	 */
	public void solveEmpty(List<String> boardArray) {
		initialBoard = new Board(boardArray);

		queue.clear();
		solvedBoards.clear();

		for (int r = 0; r < initialBoard.size; r++)
			for (int c = 0; c < initialBoard.size; c++)
				if (initialBoard.get(r, c) == ' ') {
					initialBoard.place(r, c, 'S');
					queue.add(new Board(initialBoard, r, c));
					initialBoard.place(r, c, ' ');
				}
		moves = 0;
		while (queue.size() != 0 && solvedBoards.isEmpty()) {
			int queueLength = queue.size();
			for (int i = 0; i < queueLength; i++)
				findPath(queue.remove());
			moves++;
		}
		printResult();
	}


	private void printResult() {
		println("map");

		for (var solvedBoard : solvedBoards) {
			println(moves + " moves");
			println("solution");
			println(solvedBoard);
			println("endsolution");
		}

		if (solvedBoards.isEmpty()) {
			println("no solution");
			println(initialBoard);
		}

		println("endmap");
	}

	int initRow;
	int initColumn;
	Board temp;

	/**
	 * general path finding algorithm that checks each direction, only returning a
	 * board if the cursor moved somewhere
	 * 
	 * @param direction
	 * @param board
	 */
	private void findPath(Board board) {
		initRow = board.row;
		initColumn = board.column;
		for (char direction : way.keySet()) {
			temp = new Board(board);
			while (temp.get(temp.row + way.get(direction)[0], temp.column + way.get(direction)[1]) == ' ') {
				temp.row += way.get(direction)[0];
				temp.column += way.get(direction)[1];
				temp.place(temp.row, temp.column, direction);
			}
			if (initRow != temp.row || initColumn != temp.column)
				if (temp.find(' ') == null) {
					temp.place(temp.row, temp.column, 'F');
					solvedBoards.add(temp);
				} else if (solvedBoards.isEmpty() && checkViable(temp))
					queue.add(temp);
		}
	}

	int filled;
	int blanks;

	/**
	 * checks if the board still has only one region, if there are more than one
	 * region(s) it's impossible for there to be a fullboard
	 * 
	 * @param board
	 * @return whether or not the board is still able to be a fullboard
	 */
	private boolean checkViable(Board board) {
		Board temp = new Board(board);
		int[] position = temp.find(' ');
		blanks = temp.count(' ');
		filled = 0;
		check(position[0], position[1], temp);
		if (filled != blanks)
			return false;
		return true;
	}


	private void check(int row, int column, Board board) {
		filled++; // counts the amount of spots filled
		board.place(row, column, 'X');
		for (char direction : way.keySet()) {
			if (board.get(row + way.get(direction)[0], column + way.get(direction)[1]) == ' ')
				check(row + way.get(direction)[0], column + way.get(direction)[1], board);
			if (filled == blanks) // removes unnecessary work
				return;
		}
	}
}
