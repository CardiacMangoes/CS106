package rockcountdown;

import static sbcc.Core.*;

public class Song {
	private int rank;
	private String title;
	private String artist;

	public Song(int rank, String title, String artist) {
		super();
		this.rank = rank;
		this.title = title;
		this.artist = artist;
	}


	public Song(String entry) {
		super();
		var entries = entry.split("\t");
		this.rank = parseInt(entries[0]);
		this.title = entries[1].trim();
		this.artist = entries[2].trim();
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}
}
