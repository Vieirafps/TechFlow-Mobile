package com.techflowsupport;

import android.app.Application;
import android.util.Log;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Log.d("TechFlow", "Aplicativo iniciado com sucesso!");
    }

    public static App getInstance() {
        return instance;
    }
}
