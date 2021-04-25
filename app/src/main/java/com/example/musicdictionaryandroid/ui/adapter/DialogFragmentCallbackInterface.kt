package com.example.musicdictionaryandroid.ui.adapter

import com.example.musicdictionaryandroid.domain.model.entity.Artist

/**
 * ダイアログ用コールバックリスナー
 *
 */
interface DialogFragmentCallbackInterface {
    fun callBackMethod(data: Artist)
}
