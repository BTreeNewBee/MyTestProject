package iguigui.jna;

import com.sun.jna.win32.StdCallLibrary;

// kernel32.dll uses the __stdcall calling convention (check the function
// declaration for "WINAPI" or "PASCAL"), so extend StdCallLibrary
// Most C libraries will just extend com.sun.jna.Library,
public interface Kernel32 extends StdCallLibrary {
    // Method declarations, constant and structure definitions go here


}