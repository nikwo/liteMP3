package mediaplayer;

import java.io.File;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import parser.MetaData;


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
    }

    public void play(ListView<String> lv) {
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        player.play();
        player.setOnEndOfMedia(()->{
            next(lv);
        });
    }

    public void play(int index, ListView<String> lv){
        track_path = tracks.get(index).get_track_path();
        player = new MediaPlayer(new Media(new File(track_path).toURI().toString()));
        player.setVolume(0.2);
        player.play();
        ind = index;
        player.setOnEndOfMedia(()-> next(lv));
    }

    public void stop(){
        player.stop();
    }

    public void next(ListView<String> lv){
        ind++;
        select_cell(lv);
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        play(lv);
    }

    public void prev(ListView<String> lv){
        ind--;
        select_cell(lv);
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        play(lv);
    }

    public MediaPlayer.Status get_state(){
        return player.getStatus();
    }

    public void pause(){
        player.pause();
    }

    public void resume(){
        player.play();
    }

    private void select_cell(ListView<String> lv){
        lv.getSelectionModel().clearAndSelect(ind);
    }

    public int get_ind(){
        return ind;
    }
}
