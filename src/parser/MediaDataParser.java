package parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaDataParser {
    private String root;
    private List<File> file_list;
    public MediaDataParser(String dir_path) {
        root = dir_path;
        file_list = new ArrayList<>();
    }
    private void RecursiveParse(String dir) {
        File directory = new File(dir);
        File[] fList = directory.listFiles();
        if (fList != null){
            for (File file : fList) {
                if (file.isFile()) {
                    if(file.getName().contains("mp3"))
                        file_list.add(file);
                } else if (file.isDirectory()) {
                    RecursiveParse(file.getAbsolutePath());
                }
            }
        }
    }
    public void parse(){
        RecursiveParse(root);
    }
    public String get_root_dir(){
        return root;
    }
    public List<File> getFile_list(){
        return file_list;
    }
}
