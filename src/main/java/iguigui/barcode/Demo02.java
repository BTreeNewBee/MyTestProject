package iguigui.barcode;

import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.output.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Demo02 {
    public static void main(String[] args) throws IOException {

        String s = "测试测试我测试测试我测试测试";

        var width = 406;
        var height = 1000;
        var image = new BufferedImage(width, height, TYPE_INT_RGB);
        var g = image.createGraphics();
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        var fontSize = 40f;
        var tmpY = fontSize * 1.5f;
        var yStep = fontSize;
        var strList = s.split("\n");
        for (String s1 : strList) {
            var tl = new TextLayout(s1, g.getFont(), g.getFontRenderContext());
            tl.draw(g, fontSize, tmpY);
            tmpY += yStep;
        }

        Code128 barcode = new Code128();
        barcode.setFontName("Monospaced");
        barcode.setFontSize(16);
        barcode.setModuleWidth(2);
        barcode.setBarHeight(50);
        barcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
        barcode.setContent("123456789");
        barcode.setQuietZoneHorizontal((width - barcode.getWidth()) / 2);
        barcode.setQuietZoneVertical((int) (height*0.8));

        Java2DRenderer renderer = new Java2DRenderer(g, 1, uk.org.okapibarcode.graphics.Color.WHITE, uk.org.okapibarcode.graphics.Color.BLACK);
        renderer.render(barcode);

        ImageIO.write(image, "png", new File("C:\\bot\\2.png"));

    }
}
