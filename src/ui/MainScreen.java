package ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;

public class MainScreen extends Group {
    private Group screen;
    private GridPane grid;
    private AnchorPane pane;
    private Label program_name;
    private Label track_name;
    public MainScreen(){
        screen = new Group();
        grid = new GridPane();
        pane = new AnchorPane();
        program_name = new Label("LiteMP3");
        track_name = new Label();
    }
    public Group get_screen() {
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(77, 69, 99), CornerRadii.EMPTY, Insets.EMPTY)));

        program_name.setBackground(new Background(new BackgroundFill(Color.rgb(36, 30, 57), CornerRadii.EMPTY, Insets.EMPTY)));
        program_name.setTextFill(Color.web("#f57c00"));
        program_name.setFont(new Font("Georgia", 24));
        program_name.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(program_name, 0.0);
        AnchorPane.setLeftAnchor(program_name, 0.0);
        AnchorPane.setRightAnchor(program_name, 0.0);
        AnchorPane.setBottomAnchor(program_name, 0.0);
        pane.getChildren().add(program_name);

        track_name.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        track_name.setTextFill(Color.web("#f5c493"));
        track_name.setFont(new Font("Georgia", 24));
        track_name.setAlignment(Pos.TOP_CENTER);
        track_name.setText("Track name");
        track_name.setTextAlignment(TextAlignment.JUSTIFY);
        track_name.setMinHeight(40);
        track_name.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(track_name, Priority.ALWAYS);

        Button menu_button = new Button("");
        menu_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        menu_button.setStyle("-fx-background-image: url('/ui/icons/menu_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        menu_button.setMinSize(40, 40);

        GridPane.setHalignment(menu_button, HPos.LEFT);
        GridPane.setValignment(menu_button, VPos.TOP);

        Button prev_button = new Button("");
        prev_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        prev_button.setStyle("-fx-background-image: url('/ui/icons/prev_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        prev_button.setMinSize(60,60);
        GridPane.setHalignment(prev_button, HPos.LEFT);
        GridPane.setValignment(prev_button, VPos.BOTTOM);

        Region track_pic = new Region();
        track_pic.setStyle("-fx-background-image: url('/ui/icons/no_pic_icon.png'); "+
                "-fx-background-size: cover;");
        track_pic.setMinSize(300, 300);

        grid.add(menu_button, 0,0);
        grid.add(track_name, 1, 0, 3, 1);
        grid.add(prev_button, 0,2, 2, 1);
        grid.add(track_pic,0,1, 4, 1);
        grid.setLayoutY(29);

        screen.getChildren().add(pane);
        screen.getChildren().add(grid);
        return screen;
    }
    public void bind_label_resize(Scene scene){
        program_name.prefWidthProperty().bind(scene.widthProperty());
        program_name.setTextAlignment(TextAlignment.JUSTIFY);

    }
    public void bind_grid_resize(Scene scene){
        grid.prefWidthProperty().bind(scene.widthProperty());
        grid.setAlignment(Pos.TOP_CENTER);
        grid.prefHeightProperty().bind(scene.heightProperty());
    }
}
