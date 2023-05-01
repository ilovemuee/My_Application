package com.example.myapplication;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class python2 {
    String python2(String input, Context context){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("ums");
        PyObject obj = pyObject.callAttr("ums_assistant",input);
        return obj.toString();
    }
}
