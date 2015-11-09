package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;



public class RSSFeedParser {
	
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String LINK = "link";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String LASTUPDATE ="version";
	private boolean isStart =false;

	  private URL url;
	  
	  public RSSFeedParser(){
		  
	  }
	  
	  public RSSFeedParser(String feedUrl) {
		    try {
		      this.url = new URL(feedUrl);
		    } catch (MalformedURLException e) {
		      throw new RuntimeException(e);
		    }
	  }

	  public Feed readFeed() {
	    Feed feed = new Feed();
	    try {
	      boolean isFeedHeader = true;
	      // Set header values intial to the empty string
	  String description = "";
	  String title = "";
	  String link = "";
	  String datePub = "";
	  String lastUpdate="";
	
	  // First create a new XMLInputFactory
	  XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	  // Setup a new eventReader
	  InputStream in = read();
	  XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	  // read the XML document
	  while (eventReader.hasNext()) {
	    XMLEvent event = eventReader.nextEvent();
	    if (event.isStartElement()) {
	      String localPart = event.asStartElement().getName().getLocalPart();
	      switch (localPart) {
	      case ITEM:
		        if (isFeedHeader) {
		          isFeedHeader = false;
//		              feed = new Feed(title, link, description, language,copyright, datePub);
		              feed = new Feed();
		        	   description = "";
		        	   title = "";
		        	   link = "";
		        	   datePub = "";
		        	   lastUpdate="";
	            }
	        	isStart =true;
	            event = eventReader.nextEvent();
	            break;
	          case TITLE:
	            title = getCharacterData(event, eventReader);
	            break;
	          case LASTUPDATE:
		            lastUpdate = getCharacterData(event, eventReader);
		            break;
	          case DESCRIPTION:
	            description = getCharacterData(event, eventReader);
	            break;
	          case LINK:
	            link = getCharacterData(event, eventReader);
	            break;
	          case PUB_DATE:
	        	  datePub = getCharacterData(event, eventReader);
	            break;
	          }
	        } else if (event.isEndElement()) {
	        	
	          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
	        	  FeedMessage message = new FeedMessage();
	        	  if(isStart){	        		 
		            
		            message.setDescription(description);
		            message.setDatePub(datePub);
		            message.setLink(link);
		            message.setTitle(title);
		            message.setLastUpdate(lastUpdate);
		            feed.addMessagesFeed(message);
		            event = eventReader.nextEvent();
		            continue;
	            }
	          }
	          
	        }
	      }
	    } catch (XMLStreamException e) {
	      throw new RuntimeException(e);
	    }
	    return feed;
	  }

		  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
		      throws XMLStreamException {
		    String result = "";
		    event = eventReader.nextEvent();
		    if (event instanceof Characters) {
		      result = event.asCharacters().getData();
		    }
		    return result;
		  }

		  private InputStream read() {
		    try {
		      return url.openStream();
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
		  }
	  

}
