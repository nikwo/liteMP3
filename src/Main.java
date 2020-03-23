import cfg.Config;
import db.DatabaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import parser.MediaDataParser;
import parser.MetaData;
import ui.MainScreen;

import java.io.File;
import java.util.List;


public class Main extends Application{
    static private Config config = new Config();
    public static void main(String[] args){
        config.read_cfg();
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainScreen screen = new MainScreen();
        Scene scene = new Scene(screen.get_screen());
        stage.setTitle("LiteMP3");
        stage.setWidth(1000);
        stage.setHeight(800);
        screen.bind_grid_resize(scene);

        DatabaseController db = new DatabaseController();
        db.init(config.is_statred());

        stage.setScene(scene);
        stage.show();
    }
}
