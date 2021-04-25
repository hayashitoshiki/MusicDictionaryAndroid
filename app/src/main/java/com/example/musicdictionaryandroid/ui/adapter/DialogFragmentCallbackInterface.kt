package com.example.musicdictionaryandroid.ui.adapter

import com.example.musicdictionaryandroid.data.database.entity.ArtistsForm

/**
 * ダイアログ用コールバックリスナー
 *
 */
interface DialogFragmentCallbackInterface {
    fun callBackMethod(data: ArtistsForm)
}
