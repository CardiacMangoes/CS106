package spellchecker;

import static sbcc.Core.*;

import java.util.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {

	private BasicDictionary dictionary = new BasicDictionary();
	private String text;
	private Matcher m;
	private int difference;

	@Override
	public void importDictionary(String filename) throws Exception {
		this.dictionary.importFile(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		this.dictionary.load(filename);
	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		this.dictionary.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		this.text = readFile(filename);

	}


	@Override
	public void saveDocument(String filename) throws Exception {
		writeFile(filename, text);
	}


	@Override
	public String getText() {
		return text;
	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		if (!continueFromPrevious)
			m = Pattern.compile("\\b[\\w']+\\b").matcher(text);

		while (m.find()) {
			var suggestions = dictionary.find(m.group());
			if (suggestions != null)
				return new String[] { m.group(), String.valueOf(m.start() + difference), suggestions[0],
						suggestions[1] };
		}
		return null;
	}


	@Override
	public void addWordToDictionary(String word) {
		this.dictionary.add(word);
	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		int length = text.length();
		this.text = text.substring(0, startIndex) + replacementText + text.substring(endIndex, length);
		this.difference = text.length() - length;
	}

}
