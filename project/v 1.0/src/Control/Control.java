/*
 * Author : ayadi Mohammed
 * email : ayadi.mohamed@outlook.com
 * Software : Security Feeds
 * version : 1.0
 */

package Control;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import model.Feed;
import model.FeedMessage;
import model.RSSFeedParser;
import model.AvoidSSLVerification.TrustModifier;

public class Control {
	public static String version;
	private RSSFeedParser parser;
	
	public Control()
	{
		parser = new RSSFeedParser();
		version = "1.0";
	}
	
	
	public Feed getFeedThatContaintMessages(String url){
		parser = new RSSFeedParser(url);
		Feed feed = parser.readFeed();
		return feed;
	}
	
	public String[][] getDataString(List<FeedMessage> myListOfInfos2){
		//String[] columns = {"Title","Description","link","date"};		
		String[][] data = null;		
		if(myListOfInfos2.size()!=0){
			data = new String[myListOfInfos2.size()][4];
			for(int i=0;i<myListOfInfos2.size();i++){
				data[i][0]=String.valueOf(myListOfInfos2.get(i).getTitle());
				data[i][1]=String.valueOf(myListOfInfos2.get(i).getDescription());
				data[i][2]=String.valueOf(myListOfInfos2.get(i).getLink());
				data[i][3]=String.valueOf(myListOfInfos2.get(i).getDatePub());				
			}
		}		
		return data;		
	}
	// ------Parser (get data from website)
	
	public Feed getDataForExploit(String url)
	{
		parser = new RSSFeedParser(getLinkTosearchForExploit(url));
	    Feed feed = parser.readFeed();
	    return feed;
	}
	
	public Feed getDataForNews(String url)
	{
		parser = new RSSFeedParser(getLinkToSearchForNews(url));
	    Feed feed = parser.readFeed();
	    return feed;
	}
	
	// ------searching
	
	/*
	 * @param this is the word that 
	 * this method return the url for searching a exploit,files
	 */
	private String getLinkTosearchForExploit(String exploit)
	{		
		return "https://rss.packetstormsecurity.com/search/files/?q="+exploit;
	}
	
	/*
	 * this 
	 */
	public static String getLinkToSearchForNews(String news)
	{		
		return "https://rss.packetstormsecurity.com/search/news/?q="+news; 
	}
	
	// --------- packetstormsecurity redirect the link to another page so we can get it with this function
	private String getTheLinkFromRedirectUrl(String url){
		String location="";
		url = "https://packetstormsecurity.com/files/download/"+url.substring(38);//https://packetstormsecurity.com/files/  *start here*   134214/dsa-3392-1.txt
		
		try {
	        HttpURLConnection con = (HttpURLConnection)(new URL(url).openConnection());
	        con.setInstanceFollowRedirects( false );
	        con.connect();
	        int responseCode = con.getResponseCode();
	        //System.out.println( responseCode );
	        location = con.getHeaderField("Location");
	        //System.out.println( location );
	        
		} catch (Exception e) {
			e.getMessage();
		}
		return location;
	}
	
	
	public String getContentFromTxt(String myUrl){
		// ------------- Get the content
		myUrl = getTheLinkFromRedirectUrl(myUrl);
		BufferedReader in=null;
		String inputLine ="";
		String myContent = "\n";
        try {
			URL url = new URL(myUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			TrustModifier.relaxHostChecking(connection); // here's where the magic happens
			connection.setDoOutput(true);	
			 in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			while ((inputLine = in.readLine()) != null){ 	
				myContent +=inputLine.concat("\n");
			}
			in.close();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e2){
			e2.printStackTrace();
		}
		return myContent;
	}
	
	// check for version 
	public String getLastestVersion(){
	  Feed myFeed = getFeedThatContaintMessages("https://gist.githubusercontent.com/ayadim/5fbe4ae66f7389b6e037/raw/securityFeeds.xml");//get version
		if(myFeed.getMessages().size()==0){
			return Control.version;
		}
	      return (myFeed.getMessages().get(0).getLastUpdate());
	}
	
	public static void openLinkInBrowser(String link){
		try {
			URI url = new URI(link);
			if (Desktop.isDesktopSupported()) {
			        Desktop.getDesktop().browse(url);
		    } 
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
