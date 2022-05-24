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
            void callback(int msg, String detail_json, String custom_arg);
        }
        //第一步，加载资源
        boolean AsstLoadResource(String path);
        //选一个你喜欢的create，搞不定回调就用普通create
        Pointer AsstCreate();
        //带回调的创建实例
        Pointer AsstCreateEx(AsstApiCallback callback, String custom_arg);
        //销毁实例，释放连接
        void AsstDestroy(Pointer handle);
        //连接
        boolean AsstConnect(Pointer handle, String adb, String host, String config);
        //添加任务链
        int AsstAppendTask(Pointer handle, String type, String params);
        //运行时修改参数
        boolean AsstSetTaskParams(Pointer handle, int taskId, String params);
        //开跑！
        boolean AsstStart(Pointer handle);
        //爷不想跑了爷要自己玩
        boolean AsstStop(Pointer handle);

        /**
         * 获取最后一次截图的内容
         * （但是我没成功过
         * @param handle Maa实例
         * @param buff 图片PNG格式编码内容
         * @param buff_size byte[]的长度
         * @return 图片长度
         */
        long AsstGetImage(Pointer handle, byte[] buff, long buff_size);
        //模拟点击
        boolean AsstCtrlerClick(Pointer handle, int x, int y, boolean block);
        //获取版本号
        String AsstGetVersion();

        //向maa注入日志
        void AsstLog(String level, String message);

    }


    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\atmzx\\Desktop\\MeoAssistantArknights3";
        System.setProperty("jna.library.path", path);
        MeoAssistant instance = Native.load("MeoAssistant", MeoAssistant.class);
        System.out.println("获取Maa版本号:" + instance.AsstGetVersion());
        boolean b = instance.AsstLoadResource(path);
        System.out.println("Maa加载:" + b);
//        Pointer pointer = instance.AsstCreate();
        Pointer pointer = instance.AsstCreateEx((msg, detail_json, custom_arg) -> {
            System.out.printf("回调msg : %s , 回调 detail_json : %s ,回调 custom_arg : %s \n",msg,detail_json,custom_arg);
        },"瞎写的参数理论上我应该填一个实例ID的");
        System.out.println("Maa 创建:" + pointer);
        boolean adb = instance.AsstConnect(pointer, path + "/platform-tools/adb.exe", "127.0.0.1:62001", "");
        System.out.println("Maa 尝试连接:" + adb);
        instance.AsstAppendTask(pointer, "Fight", "{\"stage\":\"LastBattle\"}");
        instance.AsstStart(pointer);
        System.out.println("阻塞等待执行");
        new Scanner(System.in).next();
        instance.AsstStop(pointer);
        instance.AsstDestroy(pointer);
    }

}


