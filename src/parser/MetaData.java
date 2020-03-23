package parser;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;

public class MetaData {
    private String _track_name, _group_name, _genre, _year, _track_path, _image_path;
    public MetaData(String name, String group, String genre, String year){
        _track_name = name;
        _group_name = group;
        _genre = genre;
        _year = year;
    }
    public MetaData(File file){
        try {
            InputStream input = new FileInputStream(new File(file.getAbsolutePath()));
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();

            _track_name = metadata.get("title");
            _group_name = metadata.get("xmpDM:artist");
            _genre = metadata.get("xmpDM:genre");
            _year = metadata.get("xmpDM:releaseDate");
            _track_path = file.getAbsolutePath();

            // Now getting image with ffmpeg
            File img = new File(file.getParent()+"\\cover.jpg");
            if(!img.exists()) {
                ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", "\"" + file.getAbsolutePath() + "\"", "\"" + file.getParent() + "\\cover.jpg\"");
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                Process process = pb.start();
            }

            _image_path = file.getParent()+"\\cover.jpg";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set_track_path(String track_path){
        _track_path = track_path;
    }
    public void set_image_path(String image_path){
        _image_path = image_path;
    }

    public String get_genre() {
        return _genre;
    }

    public String get_group_name() {
        return _group_name;
    }

    public String get_track_name() {
        return _track_name;
    }

    public String get_year() {
        return _year;
    }

    public String get_track_path() {
        return _track_path;
    }
    public String get_image_path() {
        return _image_path;
    }
}
