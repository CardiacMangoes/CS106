package fullboard;

import static sbcc.Core.*;

import java.util.*;

/**
 * object class for Board, includes the board and the current position of the
 * player
 * 
 * @author riley
 *
 */
public class Board {
	public char[][] boardArray;
	public int row = 0;
	public int column = 0;
	public int size = 0;

	/**
	 * creates board based on string list passed
	 * 
	 * @param boardList
	 */
	public Board(List<String> boardList) {
		this.size = boardList.size();
		boardArray = new char[size][size];
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				boardArray[r][c] = boardList.get(r).charAt(c);
	}


	/**
	 * copies board
	 * 
	 * @param board
	 */
	public Board(Board board) {
		this.size = board.size;
		this.boardArray = new char[size][size];
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				this.boardArray[r][c] = board.boardArray[r][c];
		this.row = board.row;
		this.column = board.column;
	}


	/**
	 * creates board with specified position
	 * 
	 * @param board
	 * @param row
	 * @param column
	 */
	public Board(Board board, int row, int column) {
		this.size = board.size;
		this.boardArray = new char[size][size];
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				this.boardArray[r][c] = board.boardArray[r][c];
		this.row = row;
		this.column = column;
	}


	public char get(int row, int column) {
		return this.boardArray[row][column];
	}


	public void place(int row, int column, char c) {
		this.boardArray[row][column] = c;
	}


	/**
	 * finds first instance of a character
	 * 
	 * @param ch
	 * @return
	 */
	public int[] find(char ch) {
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				if (boardArray[r][c] == ch)
					return new int[] { r, c };
		return null;
	}


	public int count(char ch) {
		int count = 0;
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				if (boardArray[r][c] == ch)
					count++;
		return count;
	}


	public int replace(char ch, char rep) {
		int count = 0;
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				if (boardArray[r][c] == ch)
					boardArray[r][c] = rep;
		return count;
	}


	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++)
				build.append(boardArray[r][c]);
			build.append('\n');
		}
		return build.toString();
	}

}
