package songBean;

public class PlayEnity {
private long time=0;
private String context=null; 
public PlayEnity(long time,String context){
	this.time=time;
	this.context=context;
}
public long getTime() {
	return time;
}
public void setTime(long time) {
	this.time = time;
}
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return time+" "+context;
}

}
