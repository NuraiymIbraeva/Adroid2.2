package com.nurayim.myapplication5536.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    //Метод сохранения состояния
    public void saveBoardStart() {
        preferences.edit().putBoolean("isShown", true).apply();
    }

    public boolean isShown() {
        return preferences.getBoolean("isShown", false);

    }
    public void putString(String save,String s){
        preferences.edit().putString(save,s).apply();
    }

    public String getString(String save){
        return preferences.getString(save,"");
    }


}
