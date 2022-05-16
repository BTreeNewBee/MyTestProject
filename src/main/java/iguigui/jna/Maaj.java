package iguigui.jna;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public class Maaj {

    public interface MeoAssistant extends StdCallLibrary {
        long AsstCreate();

        boolean AsstConnect(long handle, String adb, String host, String config);

        int AsstAppendTask(long handle, String type, String params);

        String AsstGetVersion();
    }

    public static void main(String[] args) {
        System.setProperty("jna.library.path", "G:\\MeoAssistantArknights3\\");
        MeoAssistant instance = Native.load("MeoAssistant", MeoAssistant.class);
        System.out.println("获取Maa版本号" + instance.AsstGetVersion());

//        long l = instance.AsstCreate();
//        boolean adb = instance.AsstConnect(l, "adb", "127.0.0.1:62001", "");
//        System.out.println(adb);

    }
}


