package com.lulu.architecturedemo;

import android.app.Application;

/**
 * @author zhanglulu on 2019/9/29.
 * for
 */
public class App extends Application {
    private static App instance;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
