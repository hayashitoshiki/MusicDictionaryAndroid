package com.example.musicdictionaryandroid.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference制御管理
 */
public class PreferenceManager {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public PreferenceManager(Context context) {
        PreferenceManager.context = context;
    }

    /**
     * String型格納
     *
     * @param key   キー
     * @param value 格納する値
     */
    protected void setString(PreferenceKey.StringKey key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("musicDictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key.name(), value);
        editor.apply();
    }

    /**
     * String型取得
     *
     * @param key キー
     * @return キーに紐づくString型オブジェクト
     */
    protected String getString(PreferenceKey.StringKey key) {
        String defaultValue = "";
        SharedPreferences preferences = context.getSharedPreferences("musicDictionary", Context.MODE_PRIVATE);
        return preferences.getString(key.name(), defaultValue);
    }

    /**
     * int型格納
     *
     * @param key   　キー
     * @param value 格納する値
     */
    protected void setInt(PreferenceKey.IntKey key, int value) {
        SharedPreferences preferences = context.getSharedPreferences("musicDictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key.name(), value);
        editor.apply();
    }

    /**
     * int型格納
     *
     * @param key 　キー
     * @return キーに紐づくint型オブジェクト
     */
    protected int getInt(PreferenceKey.IntKey key) {
        int defaultValue = 0;
        SharedPreferences preferences = context.getSharedPreferences("musicDictionary", Context.MODE_PRIVATE);
        return preferences.getInt(key.name(), defaultValue);
    }

    /**
     * オブジェクト削除
     *
     * @param key 削除するString型のキー
     */
    protected void remove(PreferenceKey.StringKey key) {
        SharedPreferences preferences = context.getSharedPreferences("musicDictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key.name());
        editor.apply();
    }

    /**
     * オブジェクト削除
     *
     * @param key 削除するint型のキー
     */
    protected void remove(PreferenceKey.IntKey key) {
        SharedPreferences preferences = context.getSharedPreferences("musicDictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key.name());
        editor.apply();
    }
}
