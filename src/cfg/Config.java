package cfg;
import java.io.*;


public class Config {
    private Boolean first_run;
    private String theme, current_playlist;
    private Integer track_id, timeline_minutes, timeline_seconds;
    public Config(){
        first_run = true;
        theme = "DEFAULT";
        track_id = timeline_minutes = timeline_seconds = 0;
        current_playlist = "";
    }
    public void read_cfg() throws IOException {
        File config = new File("./player.cfg");
        if(!config.canRead()){
            System.out.println("Cannot read cfg file, running with default settings");
            first_run = true;
            theme = "DEFAULT";
            track_id = timeline_minutes = timeline_seconds = 0;
            current_playlist = "";
            config.createNewFile();
            write_cfg_state();
        }
        else{
            BufferedReader reader;
            try{
                reader = new BufferedReader(new FileReader("./player.cfg"));
                String line = reader.readLine();
                if(line != null){
                    line = reader.readLine();
                    if(line.equals("FIRST_RUN:TRUE")){
                        first_run = true;
                    }
                    else if(line.equals("FIRST_RUN:FALSE")){
                        first_run = false;
                    }
                    line = reader.readLine();
                    if(line.equals("THEME:DEFAULT")){
                        theme = "DEFAULT";
                    }
                    else{
                        theme = line.substring(6);
                    }
                    line = reader.readLine();
                    if(line.contains("TRACK_ID")){
                        track_id = Integer.parseInt(line.substring(9));
                    }
                    line = reader.readLine();
                    if(line.contains("MINUTES")){
                        timeline_minutes = Integer.parseInt(line.substring(8));
                    }
                    line = reader.readLine();
                    if(line.contains("SECONDS")){
                        timeline_seconds = Integer.parseInt(line.substring(8));
                    }
                    line = reader.readLine();
                    if(line.contains("CURRENT_PLAYLIST")){
                        current_playlist = line.substring(17);
                    }
                }
                reader.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public Boolean is_started(){
        return first_run;
    }

    public void set_start_flag(Boolean flag){
        first_run = flag;
    }

    public String get_current_playlist(){
        return current_playlist;
    }
    public void set_current_playlist(String playlist){
        current_playlist = playlist;
    }
    public void write_cfg_state() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./player.cfg")));
        bw.write("# config file for player\n");
        bw.write("FIRST_RUN:"+String.valueOf(first_run).toUpperCase()+"\n");
        bw.write("THEME:"+theme.toUpperCase()+"\n");
        bw.write("TRACK_ID:"+track_id+"\n");
        bw.write("MINUTES:"+timeline_minutes+"\n");
        bw.write("SECONDS:"+timeline_seconds+"\n");
        bw.write("CURRENT_PLAYLIST:" + current_playlist + "\n");
        bw.close();
    }
}
