package xmlvalidator;

import static org.apache.commons.lang3.StringUtils.*;
import static sbcc.Core.*;

import java.util.*;
import java.util.regex.*;

public class BasicXmlValidator implements XmlValidator {

	BasicXmlTagStack stack = new BasicXmlTagStack();

	@Override
	public List<String> validate(String xmlDocument) {
		var m = Pattern.compile("<([^>?! ]+)([^>]*)").matcher(xmlDocument);

		while (m.find()) {
			if (m.group(1).charAt(0) == '/') {
				if (stack.getCount() == 0)
					return Arrays.asList("Orphan closing tag", substringAfter(m.group(1), "/"),
							line(xmlDocument, m.start()));
				if (compare(stack.peek(0).name, substringAfter(m.group(1), "/")) != 0)
					return Arrays.asList("Tag mismatch", stack.peek(0).name, line(xmlDocument, stack.peek(0).index),
							substringAfter(m.group(1), "/"), line(xmlDocument, m.start()));
				stack.pop();
			} else {
				if (m.group(2).length() > 0)
					if (!m.group(2).contains("\""))
						return Arrays.asList("Attribute not quoted", m.group(1), line(xmlDocument, m.start()),
								substringBetween(m.group(2), " ", "="), line(xmlDocument, m.start()));
				stack.push(new XmlTag(m.group(1), m.start()));
			}
		}

		if (stack.getCount() > 0)
			return Arrays.asList("Unclosed tag at end", stack.peek(0).name, line(xmlDocument, stack.peek(0).index));

		return null;
	}


	private String line(String str, int index) {
		return "" + (countMatches(str.substring(0, index), '\n') + 1);
	}
}