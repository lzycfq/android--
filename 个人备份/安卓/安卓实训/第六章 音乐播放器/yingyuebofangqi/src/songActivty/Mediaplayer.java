package songActivty;

import android.media.MediaPlayer;
import songTab.TabShowMusic;

public class Mediaplayer {
	public static int state = 1;
	public static int style = 4;
	private static MediaPlayer media;

	private static MediaPlayer getMedia() {
		if (media == null) {
			
			 media = new MediaPlayer();
			 media.start();
		}
		return media;

	}
	private static MediaPlayer.OnCompletionListener CompletionListener =null;
	private static String path;
	public static void DoPlayer(final String path) {
		Mediaplayer.path=path;
		try {
			getMedia().reset();//ÖØÖÃ
			getMedia().setDataSource(path);
			getMedia().prepare();
			getMedia().start();
			if (CompletionListener ==null) {


				CompletionListener=new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						TabShowMusic.adapter.notifyDataSetChanged();
						if (TabShowMusic.Music_index == TabShowMusic.listdata
								.size() - 1) {
							TabShowMusic.Music_index = 0;
						} else {
							TabShowMusic.Music_index++;
						}
						DoPlayer(TabShowMusic.listdata
								.get(TabShowMusic.Music_index).getMusic_Path());
					}

				};
			}
			getMedia().setOnCompletionListener(CompletionListener);
		} catch (Exception e) {
		}
	}
	public  static void setOnCompletionListener(MediaPlayer.OnCompletionListener CompletionListener){
		if (CompletionListener ==null) {
			CompletionListener=new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					TabShowMusic.adapter.notifyDataSetChanged();
					if (TabShowMusic.Music_index == TabShowMusic.listdata
							.size() - 1) {
						TabShowMusic.Music_index = 0;
					} else {
						TabShowMusic.Music_index++;
					}
					DoPlayer(TabShowMusic.listdata
							.get(TabShowMusic.Music_index).getMusic_Path());
				}
			};
		}
		Mediaplayer.CompletionListener=CompletionListener;
		getMedia().setOnCompletionListener(CompletionListener);
	}
	public static void DoPause() {
		getMedia().pause();
	}

	public static void DoContinuePlay() {
		if (!media.isPlaying()) {

			getMedia().start();
		}
	}

	public static void DoStop() {
		getMedia().stop();
	}

	public static int DoGetCurrentTime() {
		return getMedia().getCurrentPosition();
	}
	public static int DoGetMaxTime() {
		return getMedia().getDuration();
	}
	public static void DoSetSeekMusic(int i) {
		if (getMedia().isPlaying()) {
			getMedia().seekTo(i);
			getMedia().start();
		}else {
			getMedia().seekTo(i);
			getMedia().start();
			getMedia().pause();
		}
	}
}
