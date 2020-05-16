package sortcomparison;

import static sbcc.Core.*;

import java.util.*;

import static java.lang.Math.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * 
 * @author your_name_here
 *
 */
public class Main {

	public static void main(String[] args) {
		BasicSorter Sort = new BasicSorter();
		String[] data = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "l", "n", "o", "p", "q" };
		Collections.shuffle(Arrays.asList(data));
		println(Arrays.toString(data));
		Sort.insertionSort(data, 0, data.length);
		println(Arrays.toString(data));
	}

}
