package songBean;

import android.graphics.Bitmap;

public class MusicModel {
private String  Music_Id;
private String  Music_Name;
private String  Music_ARTIST;
private String  Music_Path;
private String  Music_ALBUM_ID;
private Bitmap  ALBUM_Image;
public MusicModel( String  Music_Id, String  Music_Name,String  Music_ARTIST,String  Music_Path,String  Music_ALBUM_ID){
	this.Music_Id=Music_Id;
	this.Music_Name=Music_Name;
	this.Music_ARTIST=Music_ARTIST;
	this.Music_Path=Music_Path;
	this.Music_ALBUM_ID=Music_ALBUM_ID;
	
}
public MusicModel(){
	super();
}

public Bitmap getALBUM_Image() {
	return ALBUM_Image;
}
public void setALBUM_Image(Bitmap aLBUM_Image) {
	ALBUM_Image = aLBUM_Image;
}
public String getMusic_Id() {
	return Music_Id;
}
public void setMusic_Id(String music_Id) {
	Music_Id = music_Id;
}
public String getMusic_Name() {
	return Music_Name;
}
public void setMusic_Name(String music_Name) {
	Music_Name = music_Name;
}
public String getMusic_ARTIST() {
	return Music_ARTIST;
}
public void setMusic_ARTIST(String music_ARTIST) {
	Music_ARTIST = music_ARTIST;
}
public String getMusic_Path() {
	return Music_Path;
}
public void setMusic_Path(String music_Path) {
	Music_Path = music_Path;
}
public String getMusic_ALBUM_ID() {
	return Music_ALBUM_ID;
}
public void setMusic_ALBUM_ID(String music_ALBUM_ID) {
	Music_ALBUM_ID = music_ALBUM_ID;
}

}
