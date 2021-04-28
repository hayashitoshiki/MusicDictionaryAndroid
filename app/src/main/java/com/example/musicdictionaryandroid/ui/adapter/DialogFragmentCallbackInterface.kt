package com.example.musicdictionaryandroid.ui.adapter

import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions

/**
 * ダイアログ用コールバックリスナー
 *
 */
interface DialogFragmentCallbackInterface {
    fun callBackMethod(data: ArtistConditions)
}
