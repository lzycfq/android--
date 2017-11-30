package ContentHandle;

import java.util.jar.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;
import RSSFeed.RSSFeed;
import RSSltem.RSSltem;

public class ContentHandler  extends DefaultHandler{
	RSSFeed rssFeed;
	RSSltem rssltem;
	String lastElemenName="";
	final int RSS_TITLE=1;
	final int RSS_LINK=2;
	final int RSS_DESCRIPTION=3;
	final int RSS_CATEGORY=4;
	final int RSS_PUBDATE=5;
	int currentstate=0;
	public void RSSHandler(){
	}
	public RSSFeed getFeed(){
	return rssFeed;
	}
	public void startDucument() throws SAXException{
	rssFeed=new RSSFeed();
	rssltem=new RSSltem();
	}
	public void endDocument() throws SAXException{
	}
	public void startElement(String namespaceURL,String localName,String qName,Attributes atts) throws SAXException{
	if(localName.equals("channel")){
	currentstate=0;
	return;}
	if(localName.equals("item")){
	rssltem=new RSSltem();
	return;}
	if(localName.equals("title")){
	currentstate=RSS_TITLE;
	return;}
	if(localName.equals("description")){
	currentstate=RSS_DESCRIPTION;
	return;
	}
	if(localName.equals("link")){
	currentstate=RSS_LINK;
	return;}
	if(localName.equals("category")){
	currentstate=RSS_CATEGORY;
	return;}
	if(localName.equals("pubDate")){
	currentstate=RSS_PUBDATE;
	return;
	}
	currentstate=0;
	}
	public void endElment(String namespaceURL,String localName,String qName) throws SAXException{
	if(localName.equals("item")){
	rssFeed.addItem(rssltem);
	return;
	}
	}

	public void charaters(char ch[],int start,int length){
	String theString=new String( ch,start,length);
	switch(currentstate)
	{
	/*cass RSS_TITLE:
	rssltem.setTitle(theString);
	currentstate=0;
	break;
	cass RSS_LINK:
	rssltem.setLink(theString);
	currentstate=0;
	break;
	cass RSS_DESCRIPTION:
	rssltem.setDescription(theString);
	currentstate=0;
	break;
	cass RSS_PUBDATE:
	rssltem.setPubddate(theString);
	break;
	cass RSS_CATEGORY:
	rssltem.setCategory(theString);
	currentstate=0;
	break;
	default:
	return;*/

		
	}
	}
}

