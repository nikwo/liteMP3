package mediaplayer;

import java.io.File;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import parser.MetaData;

import static parser.MetaData.shield_literals;

public class MP3Player {
    private String track_path;
    private MediaPlayer player;
    private List<MetaData> tracks;
    private int ind;

    public MP3Player() {
        track_path = "";
        player = null;
    }

    public MP3Player(List<MetaData> t){
        track_path = t.get(0).get_track_path();
        player = new MediaPlayer(new Media(new File(track_path).toURI().toString()));
        tracks = t;
        ind = 0;
        player.setOnEndOfMedia(this::next);
    }

    public void play() {
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        player.play();
        player.setOnEndOfMedia(this::next);
    }

    public void play(int index){
        track_path = tracks.get(index).get_track_path();
        player = new MediaPlayer(new Media(new File(track_path).toURI().toString()));
        player.setVolume(0.2);
        player.play();
        player.setOnEndOfMedia(this::next);
        ind = index;
    }

    public void stop(){
        player.stop();
    }

    public void next(){
        ind++;
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        play();
    }
}
