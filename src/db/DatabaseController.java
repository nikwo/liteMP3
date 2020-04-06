package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.tika.metadata.Metadata;
import parser.MetaData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    // АААА КОСТЫЛЬ!!!11!!!1
    static private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static private final String USER = "postgres";
    static private final String PASS = "postgres";
    Connection connection;

    public DatabaseController() {
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public Boolean init(Boolean is_first_run) {
        try {
            if (is_first_run) {
                Statement st = connection.createStatement();
                st.executeUpdate("CREATE TABLE IF NOT EXISTS genres(genre_name VARCHAR(150) PRIMARY KEY);");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS albums(album_id SERIAL PRIMARY KEY, album_name VARCHAR(150)," +
                        " path_to_image VARCHAR(200), release_year VARCHAR(4), UNIQUE(album_name, path_to_image, release_year));");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS groups_t(group_name VARCHAR(150) PRIMARY KEY);");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS tracks(track_id SERIAL PRIMARY KEY, genre VARCHAR(150) REFERENCES genres," +
                        " album SERIAL REFERENCES albums, group_name VARCHAR(150) REFERENCES groups_t, track_name VARCHAR(250)," +
                        " track_path VARCHAR(200) UNIQUE NOT NULL, duration VARCHAR(30)," +
                        " UNIQUE(genre, album, track_name, track_path, group_name, duration));");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS playlists(playlist_name VARCHAR(250) PRIMARY KEY, tracks_amount INTEGER);");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS playlist_track_rel(playlist VARCHAR(250) REFERENCES playlists," +
                        " track_id SERIAL REFERENCES tracks, PRIMARY KEY (playlist, track_id));");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void move_data_to_db(List<MetaData> data, String playlist) throws SQLException{
        Statement st = connection.createStatement();

        Boolean playlist_inserted = insert_playlist(playlist, data.size());
        for (MetaData md : data) {
            String genre = md.get_genre(), album = md.get_album_name(), image_path = md.get_image_path(),
                    group_name = md.get_group_name(), path = md.get_track_path(), year = md.get_year(),
                    track_name = md.get_track_name(), duration = md.get_duration();


            Boolean genre_inserted = insert_genre(genre), album_inserted = insert_album(album, year, image_path),
                    group_inserted = insert_group(group_name);
            ResultSet res= st.executeQuery("select album_id from albums where album_name = \'" + album +
                    "\' and path_to_image = \'" + image_path + "\' and release_year = \'" + year + "\';");
            res.next();
            Boolean track_inserted = insert_track(res.getInt(1), genre, track_name, path, group_name, duration);
            ResultSet res2 = st.executeQuery("select track_id from tracks where " +
                    "genre = \'" + genre + "\' and album = " + res.getInt(1) +
                    " and group_name = \'" + group_name + "\' and track_name = \'" + track_name +"\';");
            res2.next();
            Boolean inserted_relation = insert_relation(playlist, res2.getInt(1));
        }
    }

    private Boolean insert_genre(String genre) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO genres VALUES(\'" +
                    genre + "\');");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private Boolean insert_album(String album, String year, String image_path) {
        try {
            Statement st = connection.createStatement();
            if (!image_path.equals("null")) {
                if (!year.equals("null")) {
                    st.executeUpdate("INSERT INTO albums(album_name, path_to_image, release_year) VALUES (\'" +
                            album + "\', \'" + image_path + "\', \'" + year + "\');");
                } else {
                    st.executeUpdate("INSERT INTO albums(album_name, path_to_image, release_year) VALUES (\'" +
                            album + "\', \'" + image_path + "\'," + year + ");");
                }
            } else {
                if (year.equals("null")) {
                    st.executeUpdate("INSERT INTO albums(album_name, path_to_image, release_year) VALUES (\'" +
                            album + "\', " + image_path + ", " + year + ");");
                } else {
                    st.executeUpdate("INSERT INTO albums(album_name, path_to_image, release_year) VALUES (\'" +
                            album + "\', " + image_path + ", \'" + year + "\');");
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    private Boolean insert_group(String group){
        try{
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO groups_t VALUES (\'" +
                    group + "\');");
        }catch (SQLException e){
            return false;
        }
        return true;
    }
    private Boolean insert_track(int album_id, String genre, String track_name, String path, String group, String duration){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO tracks(genre, album, track_name, track_path, group_name, duration) VALUES(\'" +
                    genre + "\', \'" + album_id + "\', \'" + track_name +
                    "\', \'" + path + "\', \'" + group + "\', \'" + duration + "\');");
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    private Boolean insert_playlist(String playlist, int size){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO playlists VALUES (\'" + playlist + "\', " + size + ");");
        }catch (SQLException e){
            try {
                Statement st = connection.createStatement();
                st.executeUpdate("UPDATE playlists SET tracks_amount = tracks_amount + " + size + " WHERE playlist_name = \'" + playlist + "\';");
            }
            catch (SQLException ex){
                ex.printStackTrace();
                return false;
            }
            return false;
        }
        return true;
    }
    private Boolean insert_relation(String playlist, int track_id){
        try {
            Statement st = connection.createStatement();
            playlist = MetaData.shield_literals(playlist);
            st.executeUpdate("INSERT INTO playlist_track_rel VALUES (\'" + playlist + "\', " + track_id + ");");
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    public List<MetaData> get_tracks(String playlist) throws SQLException {
        Statement st;
        ResultSet res;
        try{
            st = connection.createStatement();
            res = st.executeQuery("select t.*, a.* from tracks t, albums a where " +
                    "t.track_id in (select track_id from playlist_track_rel where playlist = \'" + playlist + "\') and t.album = a.album_id;");
        }catch(SQLException e){
            return null;
        }
        List<MetaData> tracks = new ArrayList<>();
        while(res.next()){
            MetaData md = new MetaData(res.getString("track_name"), res.getString("group_name"),
                    res.getString("genre"), res.getString("release_year"));
            md.set_track_path(res.getString("track_path"));
            md.set_image_path(res.getString("path_to_image"));
            tracks.add(md);
        }
        return tracks;
    }
}
