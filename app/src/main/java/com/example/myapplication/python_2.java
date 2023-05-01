package com.example.myapplication;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class python_2 {
    python_2(String input,String device,String status, Context context){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("iris");
        PyObject obj = pyObject.callAttr("connect",input,device,status);
    }
}
