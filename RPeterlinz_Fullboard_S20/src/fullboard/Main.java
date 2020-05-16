package fullboard;

import static sbcc.Core.*;

import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * 
 * @author Riley P
 *
 */
public class Main {

	public static void main(String[] args) {
		List<String> maps = new ArrayList<String>();

		try {
			maps = readFileAsLines(readLine());
		} catch (Exception e) {
			println("File not found." + "\n" + "Complete");
		}

		ArrayList<String> buildBoard = new ArrayList<String>();

		FullBoard solver = new FullBoard();
		long start = nanoTime();
		for (String row : maps) {
			if (row.equals("endmap"))
				solver.solveEmpty(buildBoard);
			buildBoard.add(row);
			if (row.equals("map"))
				buildBoard.clear();
		}
		long end = nanoTime();
		// println((double) (end - start) / 1_000_000_000);
	}
}
