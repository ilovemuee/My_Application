package com.example.myapplication;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class FloatingLayout {
    private final Context context;
    private WindowManager windowManager;
    private View floatingView;

    public FloatingLayout(Context context) {
        this.context = context;
    }

    public void createFloatingWindow() {
        // Create WindowManager object
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        // Set position and size of view
        // Inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        floatingView = inflater.inflate(R.layout.halfscreen, null);

        // Create a new FrameLayout and add the view to it
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.addView(floatingView);

        // Set the layout parameters for the FrameLayout to match parent
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.x = 0;
        params.y = 0;
        params.width = windowManager.getDefaultDisplay().getWidth();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.START | Gravity.BOTTOM;

        // Get the WindowManager and add the FrameLayout to the window
        windowManager.addView(frameLayout, params);
    }

    public void removeFloatingWindow() {
        if (windowManager != null && floatingView != null) {
            windowManager.removeView(floatingView);
            windowManager = null;
            floatingView = null;
        }
    }
}

