package com.example.myapplication;

public class Modelclass {
    public static final int Layout_One=1;//sender message
    public static final int Layout_two=2;//receiver message
    private final int Viewtype;
    private String message;

    public Modelclass(int viewtype, String message) {
        Viewtype = viewtype;
        this.message = message;
    }

    public int getViewtype() {
        return Viewtype;
    }

    public String getMessage() {
        return message;
    }
}
