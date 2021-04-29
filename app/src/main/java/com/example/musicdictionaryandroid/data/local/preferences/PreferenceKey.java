package com.example.musicdictionaryandroid.data.local.preferences;

/**
 * Preferenceアクセスキー
 */
abstract class PreferenceKey {

    /**
     * String型指定
     */
    protected enum StringKey {
        // Eメール
        EMAIL,

        // 名前
        NAME
    }

    /**
     * int型指定
     */
    protected enum IntKey {
        // 性別
        GENDER,

        // 地域
        AREA,

        // 生年月日
        BIRTHDAY,

        // 登録数
        FAVORITE
    }

}

