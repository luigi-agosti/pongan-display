package com.giago.pongan.display;

import android.app.Application;

public class PonganApplication extends Application {

    public static SoundEffect soundEffect;
    
    public void onCreate() {
        super.onCreate();
        soundEffect = new SoundEffect(getApplicationContext());
        soundEffect.initSounds();
    }
    
    public static SoundEffect getSoundEffect(){
        return soundEffect;
    }
}
