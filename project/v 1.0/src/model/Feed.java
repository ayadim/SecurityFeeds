package model;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	  private String link;	  
	  final List<FeedMessage> entries = new ArrayList<FeedMessage>();
	  
	  public Feed() {	   
	    
	  }
	  
	  public List<FeedMessage> getMessages() {
		  if(entries.size()==0)
			  return new ArrayList<FeedMessage>();
	    return entries;
	  }

	  public String getLink() {
	    return link;
	  }
	  
	  public void addMessagesFeed(FeedMessage item) {		  
		  if(item == null || item.getTitle().equals("") || item.getTitle() ==null)
			  return;		  
		  entries.add(item);
	  }

	  @Override
	  public String toString() {
	    return " link=" + link+ "]";
	  }
	  
}
