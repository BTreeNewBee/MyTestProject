package iguigui.opencvs;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCV {

    public static void main(String[] args) {

         // Loading the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  
        // Reading the contents of the image
        // from local computer directory
        String src = "D:\\bot\\template.png";
  
        // Creating a Mat object
        Mat image = Imgcodecs.imread(src);
  
        // Text to be added
        String text = "我的龟龟123!!!";
  
        // Points from where text should be added
        Point org = new Point(170, 280);
  
        // Color of the text
        Scalar color = new Scalar(0, 0, 255);
  
        // Fonttype of the text to be added
        int fontType = Imgproc.FONT_HERSHEY_PLAIN;
  
        // Fontsize of the text to be added
        int fontSze = 1;
  
        // Thickness of the lines in px
        int thickness = 3;
  
        // Adding text to the image using putText method
        Imgproc.putText(image, text, org, fontType,
                                        5, color, thickness);
        //save image to file
        Imgcodecs.imwrite("D:\\bot\\3.png", image);

//        // Displaying the Image after adding the Text
//        HighGui.imshow("", image);
//
//        // Waiting for a key event to delay
//        HighGui.waitKey();
    }
}
