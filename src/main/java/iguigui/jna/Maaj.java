package iguigui.jna;

import com.alibaba.fastjson.JSONObject;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

import java.util.Scanner;

public class Maaj {


    public interface MeoAssistant extends StdCallLibrary {

        //回调接口，在后面实现
        interface AsstApiCallback extends StdCallCallback {
            /**
             * Return whether to continue enumeration.
             */
            void callback(int msg, String detail_json, Pointer custom_arg);
        }

        //第一步，加载资源
        boolean AsstLoadResource(String path);

        //选一个你喜欢的create，搞不定回调就用普通create
        Pointer AsstCreate();

        Pointer AsstCreateEx(AsstApiCallback callback);

        void AsstDestroy(Pointer handle);

        //连接安卓
        boolean AsstConnect(Pointer handle, String adb, String host, String config);

        //添加任务链
        int AsstAppendTask(Pointer handle, String type, String params);

        //运行时修改参数
        boolean AsstSetTaskParams(Pointer handle, int taskId, String params);

        //开跑！
        boolean AsstStart(Pointer handle);

        //爷不想跑了爷要自己玩
        boolean AsstStop(Pointer handle);

        //获取最后一次截图的内容，PNG格式编码
        long AsstGetImage(Pointer handle, byte[] buff, long length);

        //获取版本号
        String AsstGetVersion();

        //向maa注入日志
        void AsstLog(String level, String message);
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
        Thread.sleep(1000 * 10);
        byte[] bytes = new byte[1024 * 1024];
        long l = instance.AsstGetImage(pointer, bytes, bytes.length);
        System.out.println(l);
        instance.AsstStop(pointer);
        new Scanner(System.in).next();
    }

}


