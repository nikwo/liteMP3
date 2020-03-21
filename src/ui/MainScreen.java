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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;

public class MainScreen extends Group {
    private Group screen;
    private GridPane grid;
    private Label program_name;
    private Label track_name;
    public MainScreen(){
        screen = new Group();
        grid = new GridPane();
        program_name = new Label("LiteMP3");
        track_name = new Label();
    }
    public Group get_screen() throws FileNotFoundException {
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(77, 69, 99), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setVgap(10);
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS);
        col0.setHalignment(HPos.LEFT);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        col1.setHalignment(HPos.LEFT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        col2.setHalignment(HPos.CENTER);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.ALWAYS);
        col3.setHalignment(HPos.RIGHT);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(Priority.ALWAYS);
        col4.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().addAll(col0, col1, col2, col3, col4);
        RowConstraints row0 = new RowConstraints();
        row0.setVgrow(Priority.NEVER);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.NEVER);
        grid.getRowConstraints().addAll(row0, row1, row2);

        program_name.setBackground(new Background(new BackgroundFill(Color.rgb(36, 30, 57), CornerRadii.EMPTY, Insets.EMPTY)));
        program_name.setTextFill(Color.web("#f57c00"));
        program_name.setFont(new Font("Georgia", 24));
        program_name.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(program_name, 0.0);
        AnchorPane.setLeftAnchor(program_name, 0.0);
        AnchorPane.setRightAnchor(program_name, 0.0);
        AnchorPane.setBottomAnchor(program_name, 0.0);

        track_name.setTextFill(Color.web("#f5c493"));
        track_name.setFont(new Font("Georgia", 24));
        track_name.setAlignment(Pos.TOP_CENTER);
        track_name.setText("Track name");
        track_name.setTextAlignment(TextAlignment.JUSTIFY);
        track_name.setMinHeight(40);
        track_name.setMaxWidth(Double.MAX_VALUE);

        Button menu_button = new Button("");
        menu_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        menu_button.setStyle("-fx-background-image: url('/ui/icons/menu_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        menu_button.setMinSize(80, 80);

        Button prev_button = new Button("");
        prev_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        prev_button.setStyle("-fx-background-image: url('/ui/icons/prev_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        prev_button.setMinSize(80,80);

        Button next_button = new Button("");
        next_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        next_button.setStyle("-fx-background-image: url('/ui/icons/next_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        next_button.setMinSize(80,80);

        Button play_button = new Button("");
        play_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        play_button.setStyle("-fx-background-image: url('/ui/icons/play_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        play_button.setMinSize(80,80);

        Button cycle_button = new Button("");
        cycle_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        cycle_button.setStyle("-fx-background-image: url('/ui/icons/cycle_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        cycle_button.setMinSize(80,80);

        Button random_button = new Button("");
        random_button.setBackground(new Background(new BackgroundFill(Color.rgb(108, 79, 130), CornerRadii.EMPTY, Insets.EMPTY)));
        random_button.setStyle("-fx-background-image: url('/ui/icons/random_icon.png'); "+
                "-fx-background-size: cover; " + "-fx-background-color:transparent;");
        random_button.setMinSize(80,80);

        Image image = new Image("file:./src/ui/icons/no_pic_icon.png");
        ImageView track_pic = new ImageView(image);
        track_pic.setFitHeight(580);
        track_pic.setFitWidth(600/5*3);
        track_pic.setPreserveRatio(true);

        grid.add(menu_button, 0, 0);
        grid.add(track_name, 1, 0, 3, 1);
        //grid.add(track_pic, 1, 1, 3, 1);
        grid.add(next_button, 3, 2);
        grid.add(play_button, 2, 2);
        grid.add(prev_button, 1, 2);
        grid.add(cycle_button, 0, 2);
        grid.add(random_button, 4, 2);

        screen.getChildren().add(grid);
        return screen;
    }

    public void bind_grid_resize(Scene scene){
        grid.prefWidthProperty().bind(scene.widthProperty());
        grid.setAlignment(Pos.TOP_CENTER);
        grid.prefHeightProperty().bind(scene.heightProperty());
    }
}
