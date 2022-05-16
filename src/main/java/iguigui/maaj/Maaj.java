package iguigui.maaj;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

//试图编写一个maa的Java端调用
public class Maaj {

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.



    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
                Native.load((Platform.isWindows() ? "msvcrt" : "c"),
                        CLibrary.class);

        void printf(String format, Object... args);
    }

    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello, World\n");
        for (int i = 0; i < args.length; i++) {
            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
    }
}

