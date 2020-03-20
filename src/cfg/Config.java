package cfg;
import java.io.*;


public class Config {
    private Boolean first_run;
    private String theme;
    private Integer track_id, timeline_minutes, timeline_seconds;
    public Config(){
        first_run = true;
        theme = "DEFAULT";
        track_id = timeline_minutes = timeline_seconds = 0;
    }
    public void read_cfg(){
        File config = new File("./player.cfg");
        if(!config.canRead()){
            System.out.println("Cannot read cfg file, running with default settings");
            first_run = true;
            theme = "DEFAULT";
            track_id = timeline_minutes = timeline_seconds = 0;
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
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
