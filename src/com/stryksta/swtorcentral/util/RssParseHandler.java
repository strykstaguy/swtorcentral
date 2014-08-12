package com.stryksta.swtorcentral.util;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.stryksta.swtorcentral.data.RssItem;

public class RssParseHandler extends DefaultHandler {

	private ArrayList<RssItem> rssItems;

	private RssItem currentItem;

	private boolean parsingTitle;
	private boolean parsingDescription;
	private boolean parsingDate;
    private StringBuffer currentTitleSb;
    private StringBuffer currentDescriptionSb;
    private StringBuffer currentDateSb;
	
	String LinkType;
	String LinkRel;
	
	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}

	public ArrayList<RssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
	                         Attributes attributes) throws SAXException {

	    if ("entry".equals(qName)) {
	        currentItem = new RssItem();
	    } else if ("title".equals(qName)) {
            parsingTitle = true;
            currentTitleSb = new StringBuffer();
	    } else if ("summary".equals(qName)) {
	        parsingDescription = true;
	        currentDescriptionSb = new StringBuffer();
	    } else if ("updated".equals(qName)) {
	    	parsingDate = true;
	        currentDateSb = new StringBuffer();
	    } else if ("link".equals(qName)) {
	        LinkType = attributes.getValue("rel");
	        LinkRel = attributes.getValue("href");
	    }
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if ("entry".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
			if (currentItem != null)
            currentItem.setTitle(currentTitleSb.toString());
		} else if ("summary".equals(qName)) {
			parsingDescription = false;
			if (currentItem != null)
			currentItem.setDescription(currentDescriptionSb.toString());
		} else if ("updated".equals(qName)) {
			parsingDate = false;
			if (currentItem != null)
			currentItem.setPubDate(currentDateSb.toString());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingTitle) {
			if (currentItem != null)
				 currentTitleSb.append(new String(ch, start, length));
		} else if (parsingDescription) {
			if (currentItem != null)
				currentDescriptionSb.append(new String(ch, start, length));
		} else if (parsingDate) {
			if (currentItem != null)
				currentDateSb.append(new String(ch, start, length));
		}
		
		if (currentItem != null) {
			if (LinkType.equals("alternate")) {
				currentItem.setLink(LinkRel);	
		    } else if (LinkType.equals("enclosure")){
		    	currentItem.setImage(LinkRel);
		    }
            
         }
		
		
	}
	
	

}
