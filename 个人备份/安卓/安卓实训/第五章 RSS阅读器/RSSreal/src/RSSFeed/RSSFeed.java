package RSSFeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import RSSltem.RSSltem;



public class RSSFeed {
  private String title;
  private String pubdate;
   private int itemcount=0;
  private List<RSSltem> itemlist;
  
  public void RSSFedd(){
	  itemlist=new Vector(0);
	  
  }
  public int addItem(RSSltem item){
	 itemlist.add(item);
	 itemcount++;
	 return itemcount;
  }
  public RSSltem getItem(int location){
	  return itemlist.get(location);
  }
  public List getAllltems(){
	  return itemlist;
  }
  public List getAllltemsForListView(){
	  List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	  int size=itemlist.size();
	  for(int i=0;i<size;i++){
		  HashMap<String,Object>item=new HashMap<String,Object>();
		  item.put(RSSltem.TITLE,itemlist.get(i).getTitle());
		  item.put(RSSltem.PUBDATE,itemlist.get(i).getPubddate());
		  data.add(item);
	  }
	  return data;
  }
  
public String getPubdate() {
	return pubdate;
}
public void setPubdate(String pubdate) {
	this.pubdate = pubdate;
}
public int getItemcount() {
	return itemcount;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
}
