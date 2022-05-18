package iguigui.jna;

import com.alibaba.fastjson.JSONObject;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

import java.util.Scanner;

public class Maaj {


    public interface MeoAssistant extends StdCallLibrary {

        interface AsstApiCallback extends StdCallCallback {
            /**
             * Return whether to continue enumeration.
             */
            void callback(int msg, String detail_json, Pointer custom_arg);
        }

        boolean AsstLoadResource(String path);

        Pointer AsstCreate();

        Pointer AsstCreateEx(AsstApiCallback callback);

        boolean AsstConnect(Pointer handle, String adb, String host, String config);

        int AsstAppendTask(Pointer handle, String type, String params);

        boolean AsstStart(Pointer handle);

        boolean AsstStop(Pointer handle);

        String AsstGetVersion();
    }


    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\atmzx\\Desktop\\MaaBundle-DevBuild-CICD-2c42604-2022-05-15-15-16-46";
        System.setProperty("jna.library.path", path);
        System.out.println(System.getenv().get("Path"));
        MeoAssistant instance = Native.load("MeoAssistant", MeoAssistant.class);
        System.out.println("获取Maa版本号:" + instance.AsstGetVersion());
        boolean b = instance.AsstLoadResource(path);
        System.out.println("Maa加载:" + b);
//        Pointer pointer = instance.AsstCreate();
        Pointer pointer = instance.AsstCreateEx((msg, detail_json, custom_arg) -> {
            System.out.println("msg =" + msg);
            System.out.println("detail_json =" + detail_json);
            System.out.println("pointer =" + custom_arg);
        });
        System.out.println("Maa 创建:" + pointer);
        boolean adb = instance.AsstConnect(pointer, path + "/platform-tools/adb.exe", "127.0.0.1:62001", "");
        System.out.println("Maa 尝试连接:" + adb);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stage", "LastBattle");
        instance.AsstAppendTask(pointer, "Fight", jsonObject.toJSONString());
        instance.AsstStart(pointer);
        instance.AsstStop(pointer);
        new Scanner(System.in).next();
    }

}


