package xmlvalidator;

import java.util.*;

public class BasicXmlTagStack implements XmlTagStack {

	private int count = 0;
	private ArrayList<XmlTag> items = new ArrayList();

	@Override
	public void push(XmlTag item) {
		items.add(item);
		count++;
	}


	@Override
	public XmlTag pop() {
		if (count > 0)
			return items.remove(--count);
		return null;
	}


	@Override
	public XmlTag peek(int position) {
		if (position < count)
			return items.get(count - position - 1);
		return null;
	}


	@Override
	public int getCount() {
		return count;
	}

}
