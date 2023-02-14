package com.example.myapplication;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class python {
    String python(String input, Context context){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("iris");
        PyObject obj = pyObject.callAttr("jarvis",input);
        return obj.toString();
    }
}
