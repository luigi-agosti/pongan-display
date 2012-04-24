package com.giago.pongan.display;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEffect {
    public static final int SOUND_PING = 1;
    public static final int SOUND_CRASH = 2;

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    private Context context;
    
    public SoundEffect(Context c){
        context = c;
    }

    public void initSounds() {
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(SOUND_PING, soundPool.load(context, R.raw.beep, 1));
        soundPoolMap.put(SOUND_CRASH, soundPool.load(context, R.raw.cat_crash, 1));
    }

    public void playSound(int sound) {
        /*
         * Updated: The next 4 lines calculate the current volume in a scale
         * of 0.0 to 1.0
         */
        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;

        /* Play the sound with the correct volume */
        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, 0, 1f);
    }

    public void ping() {
        playSound(SOUND_PING);
    }
    
    public void crash() {
        playSound(SOUND_CRASH);
    }
}
