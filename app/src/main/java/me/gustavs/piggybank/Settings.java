package me.gustavs.piggybank;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private final String PREFS = "PiggyBankSettings"; // Preferences file name
    private SharedPreferences settings;

    public Settings(Context context) {
        settings = context.getSharedPreferences(PREFS, 0);
    }

    public float getFloat(String key, float fallback) {
        return settings.getFloat(key, fallback);
    }

    public void setFloat(String key, float value) {
        settings.edit().putFloat(key, value).apply();
    }

}
