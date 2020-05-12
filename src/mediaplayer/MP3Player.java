package mediaplayer;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import parser.MetaData;


public class MP3Player{
    private String track_path;
    private MediaPlayer player;
    private List<MetaData> tracks;
    private int ind;
    private Boolean rnd;
    private Boolean cycle;


    public MP3Player() {
        track_path = "";
        player = null;
        rnd = false;
        cycle = false;
    }

    public MP3Player(List<MetaData> t){
        track_path = t.get(0).get_track_path();
        player = new MediaPlayer(new Media(new File(track_path).toURI().toString()));
        tracks = t;
        ind = 0;
        rnd = false;
        cycle = false;
    }

    public void play(ListView<String> lv, Region track_image) {
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        if(player.getStatus() != MediaPlayer.Status.PLAYING)
            player.play();
        else player.stop();
        player.setOnEndOfMedia(()-> next(lv, track_image));
        String path = tracks.get(ind).get_image_path();
        if(!path.equals(""))
            track_image.setStyle("-fx-background-image: url('"+ MetaData.to_url(tracks.get(ind).get_image_path()) +"'); "+
                "-fx-background-size: cover; ");
        else track_image.setStyle("-fx-background-image: url('/ui/icons/no_picture.png'); "+
                "-fx-background-size: cover; ");
    }

    public void play(int index, ListView<String> lv, Region track_image){
        track_path = tracks.get(index).get_track_path();
        player = new MediaPlayer(new Media(new File(track_path).toURI().toString()));
        player.setVolume(0.2);
        if(player.getStatus() != MediaPlayer.Status.PLAYING)
            player.play();
        else player.stop();
        ind = index;
        player.setOnEndOfMedia(()-> next(lv, track_image));
        String path = tracks.get(ind).get_image_path();
        if(!path.equals(""))
            track_image.setStyle("-fx-background-image: url('"+ MetaData.to_url(path) +"'); "+
                    "-fx-background-size: cover; ");
        else track_image.setStyle("-fx-background-image: url('/ui/icons/no_picture.png'); "+
                "-fx-background-size: cover; ");
    }

    public void stopM(){
        player.stop();
    }

    public void next(ListView<String> lv, Region track_image){
        if(!rnd)
            ind++;
        else{
            Random random = new Random();
            ind = random.nextInt(tracks.size());
        }
        select_cell(lv);
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        play(lv, track_image);
    }

    public void prev(ListView<String> lv, Region track_image){
        ind--;
        select_cell(lv);
        track_path = tracks.get(ind).get_track_path();
        Media m = new Media(new File(track_path).toURI().toString());
        player = new MediaPlayer(m);
        play(lv, track_image);
    }

    public MediaPlayer.Status get_state(){
        return player.getStatus();
    }

    public void pause(){
        player.pause();
    }

    public void resumeM(){
        player.play();
    }

    private void select_cell(ListView<String> lv){
        lv.getSelectionModel().clearAndSelect(ind);
    }

    public int get_ind(){
        return ind;
    }

    public void set_rnd_flag(Boolean fl){
        rnd = fl;
    }

    public void set_cycle_flag(Boolean fl){
        cycle = fl;
    }

    public Boolean get_rnd_flag(){
        return rnd;
    }

    public Boolean get_cycle_flag(){
        return cycle;
    }
}
