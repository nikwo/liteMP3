package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

        track_name.setBackground(new Background(new BackgroundFill(Color.rgb(77, 69, 99), CornerRadii.EMPTY, Insets.EMPTY)));
        track_name.setTextFill(Color.web("#f57c00"));

        screen.getChildren().add(pane);
        screen.getChildren().add(grid);
        return screen;
    }
    public void bind_label_resize(Scene scene){
        program_name.prefWidthProperty().bind(scene.widthProperty());
        program_name.setTextAlignment(TextAlignment.JUSTIFY);
    }
}
