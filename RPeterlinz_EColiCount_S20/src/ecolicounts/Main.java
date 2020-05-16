package ecolicounts;

import static sbcc.Core.*;

import java.io.*;

import org.apache.commons.lang3.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * 
 * @author Riley Peterlinz
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			String ecoli = readFile(readLine());
			char[] nucleotides = { 'A', 'C', 'G', 'T' };
			int[] count = { 0, 0, 0, 0 };

			for (int i = 0; i < ecoli.length(); i++) {
				var nucleotide = ArrayUtils.indexOf(nucleotides, ecoli.charAt(i));
				count[nucleotide]++;
			}

			for (var nucleotide : nucleotides) {
				var nucIndex = ArrayUtils.indexOf(nucleotides, nucleotide);
				println("#" + nucleotide + " = " + count[nucIndex]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
