package RSSltem;

public class RSSltem {
public static final String TITLE="titel";
public static final String PUBDATE="pubdata";
private String title=null;
private String link=null;
private String description=null;
private String category=null;
private String pubddate=null;
public RSSltem(){
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getPubddate() {
	return pubddate;
}
public void setPubddate(String pubddate) {
	this.pubddate = pubddate;
}
public String toString(){
	if(title.length()>20){
		return title.substring(0,42)+"......";
	}
	return title;
}
}
