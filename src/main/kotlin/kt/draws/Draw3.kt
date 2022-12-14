package kt.draws


import javafx.application.Application
import javafx.embed.swing.SwingFXUtils
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

fun main() {
    Application.launch()
}
// ...

fun textToImage(text: String): Image {
    val label = Label(text);
    label.setMinSize(125.0, 125.0)
    label.setMaxSize(125.0, 125.0)
    label.setPrefSize(125.0, 125.0)
    label.setStyle("-fx-background-color: white; -fx-text-fill:black;")
    label.setWrapText(true)
    val scene = Scene(Group(label))
    val img = WritableImage(125, 125)
    scene.snapshot(img)
    return img
}


fun saveToFile(image: Image) {
    val file = File("D:\\bot\\3.png")
    val bImage = SwingFXUtils.fromFXImage(image, null)
    try {
        ImageIO.write(bImage, "png", file)
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
}


class Applications : Application() {


    override fun start(stage: Stage) {

        val image = textToImage("Hello World")

        val imageView = ImageView(image)

        val saveBtn = Button("Save Image")
        saveBtn.setOnAction { e -> saveToFile(image) }

        val root = VBox(10.0, imageView, saveBtn)
        val scene = Scene(root)
        stage.setScene(scene)
        stage.setTitle("")
        stage.show()
    }

}
