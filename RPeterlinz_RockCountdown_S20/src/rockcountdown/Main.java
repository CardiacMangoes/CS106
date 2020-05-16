package rockcountdown;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

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
			String songText = readFile(readLine());
			songText.split("\r\n");
			var songs = songText.split("\r\n");

			var songList = new ArrayList<Song>();

			for (var song : songs) {
				var entry = new Song(song);
				songList.add(entry);
			}

			Collections.reverse(songList);

			StringBuilder str = new StringBuilder();
			for (var song : songList)
				str.append(song.getRank() + "\t" + song.getTitle() + "\r\n");

			println(str.toString());
			println("Complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
