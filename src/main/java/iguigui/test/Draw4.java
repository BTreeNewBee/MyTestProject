package iguigui.test;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Draw4 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        String imagePath = "resources/picture/yourImage.jpg";
        Image image = new Image(imagePath);

        ImageView imageView = new ImageView(image);

        Button saveBtn = new Button("Save Image");
        saveBtn.setOnAction(e -> saveToFile(image));

        VBox root = new VBox(10, imageView, saveBtn);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("");
        stage.show();
    }

    public static void saveToFile(Image image) {
        File outputFile = new File("C:/JavaFX/");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
