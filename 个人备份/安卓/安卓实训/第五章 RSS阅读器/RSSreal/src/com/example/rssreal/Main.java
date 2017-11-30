package com.example.rssreal;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import RSSFeed.RSSFeed;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import RSSFeed.RSSFeed;
import RSSltem.RSSltem;
import ContentHandle.ContentHandler;;
public class Main extends Activity implements OnItemClickListener {
	//public final String RSS_URL="http://www.xinhuanet.com/rss";
	
		public final String RSS_URL="http://www.xinhuanet.com/politics/news_politics.xml";
		public final String tag="RSSReader";
		private RSSFeed feed=null;
		public void onCreate(Bundle icicle){
			super.onCreate(icicle);
			setContentView(R.layout.activity_main);
			feed=getFeed(RSS_URL);
			showListView();
		}
		private RSSFeed getFeed(String urlString){
			try{
				URL url=new URL(urlString);
				
				SAXParserFactory factory=SAXParserFactory.newInstance();
				
				SAXParser parser=factory.newSAXParser();
				
				XMLReader xmlreader=parser.getXMLReader();
				
				ContentHandler rsshandler=new ContentHandler();
		        xmlreader.setContentHandler(rsshandler);
				
				lnputSource is=new lnputSource(url.openStream());
				xmlreader.parser(is);
				return rsshandler.getFeed();
			}
			catch(Exception ee){
				return null;
		}
		}
		private void showListView(){
			ListView itemlist=(ListView)findViewById(R.id.itemlist);
			if(feed==null){
				setTitle("RSS无效");
			return;}
			SimpleAdapter adapter=new SimpleAdapter(this,feed.getAllltemsForListView(),android.R.layout.simple_list_item_2,new String[]{RSSltem.TITLE,RSSltem.PUBDATE}, new int[]{android.R.id.text1,android.R.id.text2});
			itemlist.setAdapter(adapter);
			itemlist.setOnItemClickListener(this);
		itemlist.setSelection(0);
		}

		public void onltemClick(AdapterView parent,View v,int posittion,long id){
			lntent itemlist=new lntent(this,ShowdescriptionActivity.class);
			Bundle b=new Bundle();
			b.putString("title",feed.getItem(posittion).getTitle());
			b.putString("description",feed.getItem(posittion).getDescription());
			b.putString("link",feed.getItem(posittion).getLink());
			b.putString("pubdate",feed.getItem(posittion).getPubddate());
			itemlist.putExtra("android.intent.extra.rssltem",b);
			startActivityForResult(itemlist,0);
		}
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}
}
