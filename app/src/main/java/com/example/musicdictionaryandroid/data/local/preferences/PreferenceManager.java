package com.example.musicdictionaryandroid.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.musicdictionaryandroid.ui.MyApplication;

/**
 * Preference制御管理
 */
public class PreferenceManager {

    private static Context getContext() {
        return MyApplication.shered.getApplicationContext();
    }

    /**
     * String型格納
     *
     * @param key   キー
     * @param value 格納する値
     */
    protected static void setString(PreferenceKey.StringKey key, String value) {
        SharedPreferences preferences = getContext().getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE);
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
    protected static String getString(PreferenceKey.StringKey key) {
        String defaultValue = "";
        SharedPreferences preferences = getContext().getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE);
        return preferences.getString(key.name(), defaultValue);
    }

    /**
     * int型格納
     *
     * @param key   　キー
     * @param value 格納する値
     */
    protected static void setInt(PreferenceKey.IntKey key, int value) {
        SharedPreferences preferences = getContext().getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE);
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
    protected static int getInt(PreferenceKey.IntKey key) {
        int defaultValue = 0;
        SharedPreferences preferences = getContext().getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE);
        return preferences.getInt(key.name(), defaultValue);
    }

    /**
     * オブジェクト削除
     *
     * @param key 削除するString型のキー
     */
    protected static void remove(PreferenceKey.StringKey key) {
        SharedPreferences preferences = getContext().getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key.name());
        editor.apply();
    }

    /**
     * オブジェクト削除
     *
     * @param key 削除するint型のキー
     */
    protected static void remove(PreferenceKey.IntKey key) {
        SharedPreferences preferences = getContext().getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key.name());
        editor.apply();
    }
}
