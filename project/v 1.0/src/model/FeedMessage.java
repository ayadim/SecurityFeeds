package model;



public class FeedMessage{

		String title;
		String description;
		String datePub;
		String link;
		String author;
		String lastUpdate;
		
		public String getLastUpdate() {
			return lastUpdate;
		}
		
		public void setLastUpdate(String lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
		
		public FeedMessage()
		{
			 title="";
			 description="";
			 datePub="";
			 link="";
			 author="";
			 lastUpdate ="";
		}
		
	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
		  if(title==null){
			  this.title =" ";
			  return;
		  }
			  this.title = " "+title;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
		  if(description==null){
			  this.description =" ";
			  return;
		  }
	    this.description = " "+description;
	  }

	  public String getLink() {
	    return link;
	  }

	  public void setLink(String link) {
		  if(link==null){
			  this.link =" ";
			  return;
		  }
	    this.link = " "+link;
	  }

	  public String getAuthor() {
	    return author;
	  }

	  public void setAuthor(String author) {
	    this.author = author;
	  }


	public String getDatePub() {
		return datePub;
	}

	public void setDatePub(String datePub) {
		  if(datePub==null){
			  this.datePub ="";
			  return;
		  }
		this.datePub = " "+datePub;
	}
	
	  @Override
	  public String toString() {
		  return description + " link " + link;
	  }
}
