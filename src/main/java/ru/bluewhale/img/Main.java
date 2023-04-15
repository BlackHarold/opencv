package ru.bluewhale.img;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nu.pattern.OpenCV;
import org.opencv.core.Core;

public class Main extends Application {

    public static void main(String[] args) {
        //does not work without that
        OpenCV.loadLocally();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(15.0);
        root.setAlignment(Pos.CENTER);
        Button button = new Button("Выполнить");
        button.setOnAction(this::onClickButton);
        root.getChildren().add(button);
        Scene scene = new Scene(root, 400.0, 150.0);
        stage.setTitle("OpenCV " + Core.VERSION);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });

        stage.show();
    }

    private void onClickButton(ActionEvent e) {
        SceneFX.showImage(ImportImage.getMatrix(), "Текст в заголовке окна");
    }
}
