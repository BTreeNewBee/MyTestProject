package iguigui.jna;

import com.alibaba.fastjson.JSONObject;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Maaj {

    public interface MeoAssistant extends StdCallLibrary {

        boolean AsstLoadResource(String path);

        Pointer AsstCreate();

        boolean AsstConnect(Pointer handle, String adb, String host, String config);

        int AsstAppendTask(Pointer handle, String type, String params);

        boolean AsstStart(Pointer handle);
        boolean AsstStop(Pointer handle);

        String AsstGetVersion();
    }


    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\atmzx\\Desktop\\MaaBundle-DevBuild-CICD-2c42604-2022-05-15-15-16-46";
        System.setProperty("jna.library.path", path);
//        HashMap<String, String> stringStringHashMap = new HashMap<>(System.getenv());
//        stringStringHashMap.put("Path", ";" + path );
//        setEnv(stringStringHashMap);
//        System.out.println(System.getenv());
        System.out.println(System.getenv().get("Path"));
        MeoAssistant instance = Native.load("MeoAssistant", MeoAssistant.class);
        System.out.println("获取Maa版本号:" + instance.AsstGetVersion());
        boolean b = instance.AsstLoadResource(path);
        System.out.println("Maa加载:" + b);
        Pointer pointer = instance.AsstCreate();
        System.out.println("Maa 创建:" + pointer);
        boolean adb = instance.AsstConnect(pointer, path + "/platform-tools/adb.exe", "127.0.0.1:62001", "");
        System.out.println("Maa 尝试连接:" + adb);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stage", "LastBattle");
        instance.AsstAppendTask(pointer, "Fight", jsonObject.toJSONString());
        instance.AsstStart(pointer);
        new Scanner(System.in).next();
    }

    protected static void setEnv(Map<String, String> newenv) throws Exception {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException e) {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for (Class cl : classes) {
                if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newenv);
                }
            }
        }
    }
}


