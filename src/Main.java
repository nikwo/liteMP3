import cfg.Config;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import ui.MainScreen;


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
        stage.setWidth(600);
        stage.setHeight(800);
        screen.bind_label_resize(scene);

        stage.setScene(scene);
        stage.show();
    }
}
