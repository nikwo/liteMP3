import cfg.Config;
import db.DatabaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import ui.MainScreen;
import java.io.IOException;



public class Main extends Application{
    static private Config config = new Config();
    public static void main(String[] args) throws IOException {
        config.read_cfg();
        DatabaseController db = new DatabaseController();
        Boolean success = db.init(config.is_started());
        if(success){
            config.set_start_flag(false);
            config.write_cfg_state();
        }
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainScreen screen = new MainScreen(stage, config);
        Scene scene = new Scene(screen.get_screen());
        stage.setTitle("LiteMP3");
        stage.setWidth(1300);
        stage.setHeight(800);
        screen.bind_grid_resize(scene);
        screen.set_current_playlist();

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
